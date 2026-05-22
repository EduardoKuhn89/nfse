package br.com.nfse;

import br.com.nfse.dto.enuns.AmbienteEnum;
import br.com.nfse.dto.DFeResult;
import br.com.nfse.dto.DFeItem;
import br.com.nfse.utils.OkHttpUtils;
import br.com.nfse.utils.XmlUtils;
import br.com.nfse.utils.gson.GsonUtils;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.HttpUrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
  Cliente para a API de Distribuição de DF-e (Documentos Fiscais Eletrônicos)
  da NFS-e.

  Permite que contribuintes (Prestador, Tomador, Intermediário) consultem os
  documentos fiscais eletrônicos disponibilizados pelo Ambiente de Dados
  Nacional (ADN).
   
  Exemplo de uso
  
  // Consulta incremental – use 0 para iniciar do primeiro NSU disponível
  DFeResult r = NfseDistrib.builder()
      .config(configManager)
      .consultarPorUltimoNSU(ultimoNsuSalvo);
 
  // Recuperar um NSU pontual identificado como faltante
  DFeResult r = NfseDistrib.builder()
      .config(configManager)
      .consultarPorNSU(12345L);
 
  // Consultar eventos vinculados a uma chave de acesso
  DFeResult r = NfseDistrib.builder()
      .config(configManager)
      .chNFSe("...chave...")
      .consultarEventosPorChave();
  }
 
  @author eduardo
 */
public class NfseDistrib {

    private static final int DEFAULT_TIMEOUT = 30;

    private static final Map<AmbienteEnum, String> ENDPOINTS = new HashMap<>();

    static {
        ENDPOINTS.put(AmbienteEnum.HOMOLOGACAO, "https://adn.producaorestrita.nfse.gov.br/contribuintes");
        ENDPOINTS.put(AmbienteEnum.PRODUCAO, "https://adn.nfse.gov.br/contribuintes");
    }

    public static NfseDistribBuilder builder() {
        return new NfseDistribBuilder();
    }

    public static class NfseDistribBuilder {

        private OkHttpClient httpClient;
        private Protocol httpProtocol = Protocol.HTTP_1_1;
        private int timeoutSeconds = DEFAULT_TIMEOUT;

        private ConfigManager config;
        private String chNFSe;

        private String cnpjConsulta;

        private NfseDistribBuilder() {
        }

        public NfseDistribBuilder config(ConfigManager config) {
            this.config = Objects.requireNonNull(config, "config é obrigatório");
            return this;
        }

        public NfseDistribBuilder chNFSe(String chNFSe) {
            this.chNFSe = chNFSe;
            return this;
        }

        public NfseDistribBuilder cnpjConsulta(String cnpjConsulta) {
            this.cnpjConsulta = cnpjConsulta;
            return this;
        }

        public NfseDistribBuilder httpClient(OkHttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public NfseDistribBuilder httpProtocol(Protocol httpProtocol) {
            this.httpProtocol = httpProtocol;
            return this;
        }

        public NfseDistribBuilder timeout(int timeoutSeconds) {
            if (timeoutSeconds <= 0) {
                throw new IllegalArgumentException("Timeout deve ser maior que zero");
            }
            this.timeoutSeconds = timeoutSeconds;
            return this;
        }

        /*
          Obtém até 50 DF-e a partir do último NSU informado (consulta em
          lote).
                   
          Use {@code 0} para obter os primeiros documentos disponíveis.</p>
         
          Quando {@code getUltimoNSU()} do resultado for igual ao
          {@code maxNSU}, não há mais documentos no momento; aguarde ao menos 1
          hora antes de consultar novamente.
         
          @param ultimoNSU último NSU já persistido localmente (0 para começar do início)
          @return {@link DFeResult} com até 50 documentos e metadados da
          resposta   
         */
        public DFeResult consultarPorUltimoNSU(long ultimoNSU) throws Exception {
            validateConfig();

            HttpUrl.Builder urlBuilder = OkHttpUtils.toHttpUrlBuilder(this.getEndpoint() + "/DFe/" + ultimoNSU);
            addCnpjConsulta(urlBuilder);
            urlBuilder.addQueryParameter("lote", "true");

            return httpGet(urlBuilder.build().toString());
        }

        /*
          Consulta pontual de um único DF-e a partir de um NSU específico.
                   
          Use este método para recuperar um NSU identificado como faltante na
          base local.
         
          @param nsu número sequencial único do documento a recuperar
          @return {@link DFeResult} com o documento correspondente
         */
        public DFeResult consultarPorNSU(long nsu) throws Exception {
            validateConfig();

            HttpUrl.Builder urlBuilder = OkHttpUtils.toHttpUrlBuilder(this.getEndpoint() + "/DFe/" + nsu);
            addCnpjConsulta(urlBuilder);
            urlBuilder.addQueryParameter("lote", "false");  // retorna somente o NSU informado

            return httpGet(urlBuilder.build().toString());
        }

        /*
          Consulta todos os eventos vinculados a uma chave de acesso de NFS-e.
         
          @return {@link DFeResult} com os eventos encontrados          
         */
        public DFeResult consultarEventosPorChave() throws Exception {
            validateConfig();
            validateChNFSe();

            String url = String.format("%s/NFSe/%s/Eventos", getEndpoint(), chNFSe.trim());
            return httpGet(url);
        }

        private DFeResult httpGet(String url) throws Exception {
            OkHttpClient client = OkHttpUtils.createHttpClient(
                    this.httpClient,
                    this.config.getCertificado(),
                    this.httpProtocol,
                    this.timeoutSeconds
            );

            Request request = new Request.Builder()
                    .header("Accept", "application/json")
                    .url(url)
                    .get()
                    .build();

            try (Response response = client.newCall(request).execute()) {
                return toDistribResult(response);
            }
        }

        private DFeResult toDistribResult(Response response) throws IOException {
            ResponseBody body = response.body();
            if (body == null) {
                throw new IOException(OkHttpUtils.buildErrorMessage(response));
            }

            String jsonData = body.string();
            DFeResult result = GsonUtils.deserialize(jsonData, DFeResult.class);
            if (result == null) {
                result = new DFeResult();
            }

            result.setSuccessful(response.isSuccessful());
            result.setHttpCode(response.code());
            result.setHttpMessage(response.message());
            result.setUrl(response.request().url().toString());
            result.setJsonData(jsonData);

            // descomprimir ArquivoXml (GZip+Base64) de cada item do lote
            if (result.getLoteDFe() != null) {
                for (DFeItem item : result.getLoteDFe()) {
                    if (item.getArquivoXml() != null && !item.getArquivoXml().isEmpty()) {
                        try {
                            item.setXml(XmlUtils.gZipToXml(item.getArquivoXml()));
                        } catch (Exception e) {
                            item.setXml(null); // mantém nulo se a descompressão falhar
                        }
                    }
                }
            }

            return result;
        }

        private void addCnpjConsulta(HttpUrl.Builder urlBuilder) {
            if (cnpjConsulta != null && !cnpjConsulta.isEmpty()) {
                urlBuilder.addQueryParameter("cnpjConsulta", cnpjConsulta);
            }
        }

        private String getEndpoint() {
            String url = ENDPOINTS.get(this.config.getAmbiente());
            if (url == null) {
                throw new IllegalArgumentException("Ambiente não mapeado: " + this.config.getAmbiente());
            }
            return url;
        }

        private void validateConfig() {
            if (this.config == null) {
                throw new IllegalStateException("config é obrigatório");
            }
            if (this.config.getCertificado() == null) {
                throw new IllegalStateException("Certificado é obrigatório na configuração");
            }
            if (!this.config.getCertificado().isValid()) {
                throw new IllegalStateException("Certificado não é válido");
            }
        }

        private void validateChNFSe() {
            if (this.chNFSe == null || this.chNFSe.isEmpty()) {
                throw new IllegalStateException("chNFSe é obrigatória para esta operação");
            }
        }
    }
}
