package br.com.nfse;

import br.com.nfse.dto.AutorEvento;
import br.com.nfse.interfaces.HttpDataAware;
import br.com.nfse.dto.ConvenioResult;
import br.com.nfse.dto.EventoResult;
import br.com.nfse.dto.NFSeResult;
import br.com.nfse.dto.enuns.AmbienteEnum;
import br.com.nfse.dto.enuns.TipoServicoEnum;
import br.com.nfse.utils.DateUtils;
import br.com.nfse.utils.StringUtils;
import br.com.nfse.utils.XmlSigner;
import br.com.nfse.utils.XmlUtils;
import br.com.nfse.utils.XmlValidate;
import br.com.nfse.utils.gson.GsonUtils;
import br.com.nfse.xsd.TCDPS;
import br.com.nfse.xsd.TCInfPedReg;
import br.com.nfse.xsd.TCPedRegEvt;
import br.com.nfse.xsd.TE101101;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.net.ssl.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.security.*;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author eduardo
 */
public class Nfse {

    public static final String VERSION = "NFSe-1.00";

    private static final int DEFAULT_TIMEOUT = 30;

    private static final Map<AmbienteEnum, Map<TipoServicoEnum, String>> services = new HashMap<>();

    public static NfseBuilder builder() {
        return new NfseBuilder();
    }

    public static class NfseBuilder {

        private OkHttpClient httpClient;
        private Protocol httpProtocol = Protocol.HTTP_1_1;
        private int timeoutSeconds = DEFAULT_TIMEOUT;

        private ConfigManager config;
        private boolean assinar = true;
        private boolean validar = true;
        private String chNFSe;
        private String chDPS;

        private AutorEvento autor;
        private ZonedDateTime dhEvento;

        private NfseBuilder() {
            if (services.isEmpty()) {
                // Ambiente de Homologação
                Map<TipoServicoEnum, String> homologacao = new HashMap<>();
                homologacao.put(TipoServicoEnum.ADN, "https://adn.producaorestrita.nfse.gov.br");
                homologacao.put(TipoServicoEnum.SEFIN, "https://sefin.producaorestrita.nfse.gov.br/SefinNacional");
                services.put(AmbienteEnum.HOMOLOGACAO, homologacao);

                // Ambiente de Produção
                Map<TipoServicoEnum, String> producao = new HashMap<>();
                producao.put(TipoServicoEnum.ADN, "https://adn.nfse.gov.br");
                producao.put(TipoServicoEnum.SEFIN, "https://sefin.nfse.gov.br/SefinNacional");
                services.put(AmbienteEnum.PRODUCAO, producao);
            }
        }

        public String getService(AmbienteEnum ambiente, TipoServicoEnum tipoServico) {
            Map<TipoServicoEnum, String> ambienteServices = services.get(ambiente);
            if (ambienteServices == null) {
                throw new IllegalArgumentException("Ambiente não encontrado: " + ambiente);
            }

            String url = ambienteServices.get(tipoServico);
            if (url == null) {
                throw new IllegalArgumentException("Tipo de serviço não encontrado: " + tipoServico + " para o ambiente: " + ambiente);
            }

            return url;
        }

        public NfseBuilder config(ConfigManager config) {
            this.config = Objects.requireNonNull(config, "Não foram definidas as configurações!");
            return this;
        }

        public NfseBuilder assinar(boolean assinar) {
            this.assinar = assinar;
            return this;
        }

        public NfseBuilder validar(boolean validar) {
            this.validar = validar;
            return this;
        }

        public NfseBuilder chNFSe(String chNFSe) {
            this.chNFSe = validateChave(chNFSe, "chNFSe");
            return this;
        }

        public NfseBuilder chDPS(String chDPS) {
            this.chDPS = validateChave(chDPS, "chDPS");
            return this;
        }

        public NfseBuilder autor(AutorEvento autor) {
            this.autor = autor;
            return this;
        }

        public NfseBuilder dhEvento(ZonedDateTime dhEvento) {
            this.dhEvento = dhEvento;
            return this;
        }

        public NfseBuilder httpClient(OkHttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public NfseBuilder httpProtocol(Protocol httpProtocol) {
            this.httpProtocol = httpProtocol;
            return this;
        }

        public NfseBuilder timeout(int timeoutSeconds) {
            if (timeoutSeconds <= 0) {
                throw new IllegalArgumentException("Timeout deve ser maior que zero");
            }
            this.timeoutSeconds = timeoutSeconds;
            return this;
        }

        public NFSeResult enviarDps(TCDPS dps) throws Exception {
            validateConfig();

            if (dps.getInfDPS().getId() == null) {
                String inscrFederal = !StringUtils.isNullOrEmpty(dps.getInfDPS().getPrest().getCNPJ()) ? dps.getInfDPS().getPrest().getCNPJ() : dps.getInfDPS().getPrest().getCPF();
                String idDps = dps.getInfDPS().getPrest().getEnd().getEndNac().getCMun()
                        + (inscrFederal.length() == 14 ? "2" : "1")
                        + StringUtils.zerosParaEsquerda(inscrFederal, 14)
                        + StringUtils.zerosParaEsquerda(dps.getInfDPS().getSerie(), 5)
                        + StringUtils.zerosParaEsquerda(dps.getInfDPS().getNDPS(), 15);
                dps.getInfDPS().setNDPS("DPS" + idDps);
            }

            String xml = XmlUtils.objectToXml(dps);

            if (this.assinar) {
                xml = XmlSigner.builder()
                        .xml(xml)
                        .signPosition("DPS")
                        .elementToSign("infDPS")
                        .attributeId("Id")
                        .config(config)
                        .sha1()
                        .sign();
            }

            if (this.validar) {
                this.validateDps(xml);
            }

            String urlBase = this.getService(this.config.getAmbiente(), TipoServicoEnum.SEFIN);
            String url = String.format("%s/nfse", urlBase);

            JsonObject dpsXmlGZipB64 = new JsonObject();
            dpsXmlGZipB64.addProperty("dpsXmlGZipB64", XmlUtils.xmlToGzipB64(xml));

            RequestBody payload = RequestBody.create(dpsXmlGZipB64.toString(), MediaType.parse("application/json"));

            OkHttpClient client = this.createHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .post(payload)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                NFSeResult result = this.toResponseResult(response, NFSeResult.class);
                result.setSuccessful(response.isSuccessful());
                result.setDpsXml(xml);
                return result;
            }
        }

        public EventoResult cancelar(String codigoMotivo, String descricaoMotivo) throws Exception {
            validateConfig();
            validateEvento();

            TCPedRegEvt evt = this.criarCancelamento(
                    this.config.getAmbiente(),
                    autor,
                    this.chNFSe,
                    1,
                    codigoMotivo,
                    descricaoMotivo,
                    dhEvento
            );

            String xml = XmlUtils.objectToXml(evt);

            if (this.assinar) {
                xml = XmlSigner.builder()
                        .xml(xml)
                        .signPosition("pedRegEvento")
                        .elementToSign("infPedReg")
                        .attributeId("Id")
                        .config(config)
                        .sha1()
                        .sign();
            }

            String urlBase = this.getService(this.config.getAmbiente(), TipoServicoEnum.SEFIN);
            String url = String.format("%s/nfse/%s/eventos", urlBase, this.chNFSe);

            JsonObject eventoXmlGZipB64 = new JsonObject();
            eventoXmlGZipB64.addProperty("pedidoRegistroEventoXmlGZipB64", XmlUtils.xmlToGzipB64(xml));

            RequestBody payload = RequestBody.create(eventoXmlGZipB64.toString(), MediaType.parse("application/json"));

            OkHttpClient client = this.createHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .post(payload)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                EventoResult result = this.toResponseResult(response, EventoResult.class);
                result.setSuccessful(response.isSuccessful());
                return result;
            }
        }

        /**
         * O parâmetros "evt" corresponde a uma classe "TE101101", "TE101103",
         * "TE305101", "TE205208"... pacote br.com.nfse.xsd(...). Se refere ao
         * tipo/código do evento aceito pela SEFAZ
         *
         * @param evt -> TE305101, TE101103 ...
         * @param nPedRegEvento -> Nº. Sequencial (geralmente 1)
         * @return EventoResult
         * @throws java.lang.Exception
         */
        public EventoResult evento(Object evt, int nPedRegEvento) throws Exception {
            validateConfig();
            validateEvento();

            String pedRegEvento = StringUtils.zerosParaEsquerda("" + nPedRegEvento, 3);

            TCInfPedReg infPedReg = this.criarInfEvento(
                    config.getAmbiente(),
                    autor,
                    chNFSe,
                    pedRegEvento,
                    dhEvento
            );

            String className = evt.getClass().getSimpleName();
            if (!className.startsWith("TE")) {
                throw new IllegalArgumentException("Classe de evento deve começar com 'TE'");
            }

            String tpEvt = className.substring(2);
            String setterMethodName = "setE" + tpEvt;

            try {
                Method setter = infPedReg.getClass().getMethod(setterMethodName, evt.getClass());
                setter.invoke(infPedReg, evt);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Método " + setterMethodName + " não encontrado em TCInfPedReg");
            }

            String id = "PRE" + chNFSe + tpEvt + pedRegEvento;
            infPedReg.setId(id);

            TCPedRegEvt regEvt = new TCPedRegEvt();
            regEvt.setInfPedReg(infPedReg);
            regEvt.setVersao("1.00");

            String xml = XmlUtils.objectToXml(regEvt);

            if (this.assinar) {
                xml = XmlSigner.builder()
                        .xml(xml)
                        .signPosition("pedRegEvento")
                        .elementToSign("infPedReg")
                        .attributeId("Id")
                        .config(config)
                        .sha1()
                        .sign();
            }

            String urlBase = this.getService(this.config.getAmbiente(), TipoServicoEnum.SEFIN);
            String url = String.format("%s/nfse/%s/eventos", urlBase, this.chNFSe);

            JsonObject eventoXmlGZipB64 = new JsonObject();
            eventoXmlGZipB64.addProperty("pedidoRegistroEventoXmlGZipB64", XmlUtils.xmlToGzipB64(xml));

            RequestBody payload = RequestBody.create(eventoXmlGZipB64.toString(), MediaType.parse("application/json"));

            OkHttpClient client = this.createHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .post(payload)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                EventoResult result = this.toResponseResult(response, EventoResult.class);
                result.setSuccessful(response.isSuccessful());
                return result;
            }
        }

        public byte[] danfse() throws Exception {
            validateConfig();
            validateChNFSe();

            String urlBase = this.getService(this.config.getAmbiente(), TipoServicoEnum.ADN);
            String url = String.format("%s/danfse/%s", urlBase, this.chNFSe.trim());

            OkHttpClient client = this.createHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException(buildErrorMessage(response));
                }

                ResponseBody body = response.body();
                if (body == null) {
                    throw new Exception("Não foi possível obter o retorno de [" + url + "]!");
                }

                return body.bytes();
            }
        }

        public NFSeResult consultaChaveNFSe() throws Exception {
            validateConfig();

            String urlBase = this.getService(this.config.getAmbiente(), TipoServicoEnum.SEFIN);
            String url = String.format("%s/dps/%s", urlBase, this.chDPS.trim());

            OkHttpClient client = this.createHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            try (Response response = client.newCall(request).execute()) {
                NFSeResult result = this.toResponseResult(response, NFSeResult.class);
                result.setSuccessful(response.isSuccessful());
                return result;
            }
        }

        public NFSeResult consultaXml() throws Exception {
            validateConfig();

            String urlBase = this.getService(this.config.getAmbiente(), TipoServicoEnum.SEFIN);
            String url = String.format("%s/nfse/%s", urlBase, this.chNFSe.trim());

            OkHttpClient client = this.createHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            try (Response response = client.newCall(request).execute()) {
                NFSeResult result = this.toResponseResult(response, NFSeResult.class);
                result.setSuccessful(response.isSuccessful());
                return result;
            }
        }

        public ConvenioResult consultaConvenio(String codigoMunicipio) throws Exception {
            validateConfig();

            String urlBase = this.getService(this.config.getAmbiente(), TipoServicoEnum.ADN);
            String url = String.format("%s/parametrizacao/%s/convenio", urlBase, codigoMunicipio);

            OkHttpClient client = this.createHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            try (Response response = client.newCall(request).execute()) {
                ConvenioResult result = this.toResponseResult(response, ConvenioResult.class);
                return result;
            }
        }

        private TCPedRegEvt criarCancelamento(AmbienteEnum ambiente, AutorEvento autor, String chNFSe, Integer numeroPedido, String codigoMotivo, String descricaoMotivo, ZonedDateTime dhEvento) {

            String nPedRegEvento = StringUtils.zerosParaEsquerda("" + numeroPedido, 3);

            String id = "PRE" + chNFSe + "101101" + nPedRegEvento;

            TCPedRegEvt pedRegEvt = new TCPedRegEvt();
            pedRegEvt.setVersao("1.00");

            TCInfPedReg infPedReg = new TCInfPedReg();
            infPedReg.setId(id);
            infPedReg.setTpAmb(ambiente.getValue());
            infPedReg.setVerAplic(Nfse.VERSION);
            infPedReg.setDhEvento(DateUtils.formatWithZone(dhEvento));

            if (autor.getCnpjAutor() != null) {
                infPedReg.setCNPJAutor(autor.getCnpjAutor());
            } else {
                infPedReg.setCPFAutor(autor.getCpfAutor());
            }
            infPedReg.setChNFSe(chNFSe);
            infPedReg.setNPedRegEvento(nPedRegEvento);

            TE101101 e101101 = new TE101101();
            e101101.setXDesc("Cancelamento de NFS-e");
            e101101.setCMotivo(codigoMotivo);
            e101101.setXMotivo(descricaoMotivo);

            infPedReg.setE101101(e101101);

            pedRegEvt.setInfPedReg(infPedReg);

            return pedRegEvt;
        }

        private TCInfPedReg criarInfEvento(AmbienteEnum ambiente, AutorEvento autor, String chNFSe, String nPedRegEvento, ZonedDateTime dhEvento) {

            TCPedRegEvt pedRegEvt = new TCPedRegEvt();
            pedRegEvt.setVersao("1.00");

            TCInfPedReg infPedReg = new TCInfPedReg();
            infPedReg.setTpAmb(ambiente.getValue());
            infPedReg.setVerAplic(Nfse.VERSION);
            infPedReg.setDhEvento(DateUtils.formatWithZone(dhEvento));

            if (autor.getCnpjAutor() != null) {
                infPedReg.setCNPJAutor(autor.getCnpjAutor());
            } else {
                infPedReg.setCPFAutor(autor.getCpfAutor());
            }
            infPedReg.setChNFSe(chNFSe);
            infPedReg.setNPedRegEvento(nPedRegEvento);

            pedRegEvt.setInfPedReg(infPedReg);

            return infPedReg;
        }

        private void validateConfig() {
            if (this.config == null) {
                throw new IllegalStateException("Config é obrigatório");
            }
            if (this.config.getCertificado() == null) {
                throw new IllegalStateException("Certificado é obrigatório na configuração");
            }
            if (!this.config.getCertificado().isValid()) {
                throw new IllegalStateException("Certificado não é válido");
            }
        }

        private void validateEvento() {
            if (this.chNFSe == null || this.chNFSe.trim().isEmpty()) {
                throw new IllegalStateException("A chave NFS-e (chNFSe) é obrigatória para esta operação");
            }
            if (this.autor == null) {
                throw new IllegalStateException("A Autor do evento precisa ser informado");
            }
            if (this.dhEvento == null) {
                throw new IllegalStateException("A Data/Hora (dhEvento) precisa ser informada");
            }
        }

        private void validateDps(String xml) throws Exception {
            XmlValidate.builder()
                    .pathSchemas(this.config.getPathSchemas())
                    .xml(xml)
                    .xsd("DPS_v1.01.xsd")
                    .build()
                    .validate();
        }

        private void validateChNFSe() {
            if (this.chNFSe == null || this.chNFSe.trim().isEmpty()) {
                throw new IllegalStateException("chNFSe é obrigatória para esta operação");
            }
        }

        private String validateChave(String chave, String nomeCampo) {
            if (chave != null && chave.trim().isEmpty()) {
                throw new IllegalArgumentException(nomeCampo + " não pode ser vazia");
            }
            return chave;
        }

        private OkHttpClient createHttpClient() throws Exception {
            if (this.httpClient != null) {
                return this.httpClient;
            }

            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                    .protocols(Collections.singletonList(httpProtocol))
                    .connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
                    .readTimeout(timeoutSeconds, TimeUnit.SECONDS)
                    .writeTimeout(timeoutSeconds, TimeUnit.SECONDS);

            //configurar SSL/TLS do certificado para o autenticação
            createSslFactory(clientBuilder);

            return clientBuilder.build();
        }

        private void createSslFactory(OkHttpClient.Builder clientBuilder) throws Exception {
            SSLContext sslContext = createSSLContext();
            X509TrustManager trustManager = getTrustManagerDefault();

            clientBuilder.sslSocketFactory(sslContext.getSocketFactory(), trustManager);
        }

        private SSLContext createSSLContext() throws Exception {
            CertificateManager certificado = this.config.getCertificado();

            if (certificado.getCertificateBytes() == null || certificado.getCertificateBytes().length == 0) {
                throw new IllegalStateException("Bytes do certificado não estão disponíveis");
            }

            KeyStore ks = loadKeyStore(certificado);

            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

            kmf.init(ks, certificado.getPassword().toCharArray());

            // Usar protocolo SSL especificado ou TLS como padrão
            String sslProtocol = certificado.getProtocol() != null ? certificado.getProtocol() : "TLS";

            SSLContext sslContext = SSLContext.getInstance(sslProtocol);
            sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

            return sslContext;
        }

        private KeyStore loadKeyStore(CertificateManager certificado) throws Exception {
            String tipoKeyStore = "PKCS12";

            KeyStore ks;
            if (certificado.getProvider() != null) {
                ks = KeyStore.getInstance(tipoKeyStore, certificado.getProvider());
            } else {
                ks = KeyStore.getInstance(tipoKeyStore);
            }

            try (InputStream certStream = new ByteArrayInputStream(certificado.getCertificateBytes())) {
                ks.load(certStream, certificado.getPassword().toCharArray());
            }

            return ks;
        }

        private X509TrustManager getTrustManagerDefault() {
            try {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                        TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init((KeyStore) null);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                    throw new IllegalStateException("Unexpected default trust managers:" + java.util.Arrays.toString(trustManagers));
                }
                return (X509TrustManager) trustManagers[0];
            } catch (IllegalStateException | KeyStoreException | NoSuchAlgorithmException e) {
                throw new RuntimeException("Erro ao obter TrustManager padrão", e);
            }
        }

        private String buildErrorMessage(Response response) {
            return String.format("Erro HTTP %d: %s - URL: %s",
                    response.code(),
                    response.message(),
                    response.request().url());
        }

        private <T> T toResponseResult(Response response, Class<T> clazz) throws IOException {
            ResponseBody body = response.body();
            if (body == null) {
                throw new IOException(buildErrorMessage(response));
            }

            String bodyData = body.string();
            T result = GsonUtils.deserialize(bodyData, clazz);

            if (result instanceof HttpDataAware) {
                HttpDataAware httpAware = (HttpDataAware) result;
                httpAware.setHttpResult(
                        response.code(),
                        response.message(),
                        response.request().url().toString(),
                        bodyData
                );
            }

            return result;
        }

    }
}
