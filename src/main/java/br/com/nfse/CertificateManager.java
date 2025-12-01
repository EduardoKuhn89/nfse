package br.com.nfse;

import br.com.nfse.utils.FileUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.security.KeyStore;
import java.security.Provider;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import org.demoiselle.signer.core.extension.BasicCertificate;

/**
 * @author eduardo
 */
public class CertificateManager {

    private final byte[] certificateBytes;
    private final String password;
    private final KeyStore keyStore;
    private final String alias;
    private final Certificate[] certificates;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private final String razaoSocial;
    private final String email;
    private final LocalDate certVcto;
    private final boolean valid;
    private final String inscFederal;
    private final String cidade;
    private final String uf;
    private final boolean pj;
    private final String responsavelNome;
    private final String responsavelCpf;
    private final String consumerKey;
    private final String consumerSecret;
    private final Provider provider;
    private final String protocol;

    private CertificateManager(Builder builder) {
        try {

            byte[] certBytes = builder.certificateBytes;
            if (builder.certificateFile != null) {
                certBytes = FileUtils.readFileToByteArray(builder.certificateFile);
            }
            this.certificateBytes = certBytes;

            this.password = builder.password;
            this.consumerKey = builder.consumerKey;
            this.consumerSecret = builder.consumerSecret;
            this.provider = builder.provider;
            this.protocol = builder.protocol != null ? builder.protocol : "TLS";

            // Carregar KeyStore
            this.keyStore = carregarKeyStore(certBytes, builder.password, this.provider);

            // Obter alias
            this.alias = keyStore.aliases().nextElement();

            // Carregar chaves e certificados
            this.privateKey = (PrivateKey) keyStore.getKey(this.alias, builder.password.toCharArray());
            this.publicKey = keyStore.getCertificate(this.alias).getPublicKey();
            this.certificates = keyStore.getCertificateChain(this.alias);

            // Extrair informações do certificado
            X509Certificate x509Certificate = (X509Certificate) keyStore.getCertificate(this.alias);
            BasicCertificate certInfo = new BasicCertificate(x509Certificate);

            // Data de expiração e validade
            ZoneId zoneId = builder.zoneId != null ? builder.zoneId : ZoneId.systemDefault();
            this.certVcto = certInfo.getAfterDate().toInstant().atZone(zoneId).toLocalDate();
            this.valid = this.certVcto.compareTo(ZonedDateTime.now(zoneId).toLocalDate()) >= 0;

            // Informações básicas
            this.razaoSocial = certInfo.getName();
            this.email = certInfo.getEmail();

            // Extrair dados do subject DN
            String subjectDn = x509Certificate.getSubjectDN().getName();
            this.inscFederal = extrairInscricaoFederal(subjectDn);
            this.cidade = extrairCidade(subjectDn);
            this.uf = extrairUF(subjectDn);

            // Informações de pessoa física/jurídica
            this.pj = certInfo.hasCertificatePJ();
            if (this.pj) {
                this.responsavelNome = certInfo.getICPBRCertificatePJ().getResponsibleName();
                this.responsavelCpf = certInfo.getICPBRCertificatePJ().getResponsibleCPF();
            } else if (certInfo.hasCertificatePF()) {
                this.responsavelNome = certInfo.getName();
                this.responsavelCpf = certInfo.getICPBRCertificatePF().getCPF();
            } else {
                this.responsavelNome = null;
                this.responsavelCpf = null;
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar certificado: " + e.getMessage(), e);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private byte[] certificateBytes;
        private File certificateFile;
        private String password;
        private String consumerKey;
        private String consumerSecret;
        private ZoneId zoneId;
        private Provider provider;
        private String protocol;

        private Builder() {
        }

        public Builder fromFile(File certificateFile) {
            this.certificateFile = Objects.requireNonNull(certificateFile, "Arquivo de certificado não pode ser nulo");
            return this;
        }

        public Builder fromBytes(byte[] certificateBytes) {
            this.certificateBytes = Objects.requireNonNull(certificateBytes, "Bytes do certificado não podem ser nulos");
            return this;
        }

        public Builder password(String password) {
            this.password = Objects.requireNonNull(password, "Senha não pode ser nula");
            return this;
        }

        public Builder oauthCredentials(String consumerKey, String consumerSecret) {
            this.consumerKey = consumerKey;
            this.consumerSecret = consumerSecret;
            return this;
        }

        public Builder zoneId(ZoneId zoneId) {
            this.zoneId = Objects.requireNonNull(zoneId, "ZoneId não pode ser nulo");
            return this;
        }

        public Builder provider(Provider provider) {
            this.provider = provider;
            return this;
        }

        public Builder provider(String providerClassName) {
            try {
                Class<?> providerClass = Class.forName(providerClassName);
                this.provider = (Provider) providerClass.getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException
                    | InstantiationException | NoSuchMethodException | SecurityException
                    | InvocationTargetException e) {
                throw new IllegalArgumentException("Não foi possível instanciar o provider: " + providerClassName, e);
            }
            return this;
        }

        public Builder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public CertificateManager build() {
            validarConfiguracoesObrigatorias();
            return new CertificateManager(this);
        }

        private void validarConfiguracoesObrigatorias() {
            if (certificateBytes == null && certificateFile == null) {
                throw new IllegalStateException("Certificado não especificado. Use fromFile() ou fromBytes()");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new IllegalStateException("Senha do certificado é obrigatória");
            }
        }
    }

    private KeyStore carregarKeyStore(byte[] certBytes, String senha, Provider provider) throws Exception {
        String keyStoreType = "PKCS12";
        KeyStore ks;

        if (provider != null) {
            ks = KeyStore.getInstance(keyStoreType, provider);
        } else {
            ks = KeyStore.getInstance(keyStoreType);
        }

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(certBytes)) {
            ks.load(inputStream, senha.toCharArray());
        }
        return ks;
    }

    private String extrairInscricaoFederal(String subjectDn) {
        if (subjectDn.contains("CN=")) {
            String cnPart = subjectDn.split("CN=")[1];
            return cnPart.substring(cnPart.indexOf(":") + 1, cnPart.indexOf(","));
        }
        return null;
    }

    private String extrairCidade(String subjectDn) {
        if (subjectDn.contains("L=")) {
            String localidade = subjectDn.split("L=")[1];
            return localidade.substring(0, localidade.indexOf(","));
        }
        return null;
    }

    private String extrairUF(String subjectDn) {
        if (subjectDn.contains("ST=")) {
            String estado = subjectDn.split("ST=")[1];
            return estado.substring(0, estado.indexOf(","));
        }
        return null;
    }

    public byte[] getCertificateBytes() {
        return certificateBytes;
    }

    public String getPassword() {
        return password;
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    public String getAlias() {
        return alias;
    }

    public Certificate[] getCertificates() {
        return certificates;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCertVcto() {
        return certVcto;
    }

    public boolean isValid() {
        return valid;
    }

    public String getInscFederal() {
        return inscFederal;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public boolean isPj() {
        return pj;
    }

    public String getResponsavelNome() {
        return responsavelNome;
    }

    public String getResponsavelCpf() {
        return responsavelCpf;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getProtocol() {
        return protocol;
    }

    public static KeyStore getKeyStore(byte[] cert, String senha) throws Exception {
        KeyStore keyStoreAss = KeyStore.getInstance("PKCS12");
        try (ByteArrayInputStream bs = new ByteArrayInputStream(cert)) {
            keyStoreAss.load(bs, senha.toCharArray());
        }
        return keyStoreAss;
    }

    public static PrivateKey getPrivateKey(KeyStore ks, String alias, String senha) throws Exception {
        return (PrivateKey) ks.getKey(alias, senha.toCharArray());
    }

    public static PublicKey getPublicKey(KeyStore ks, String alias) throws Exception {
        return ks.getCertificate(alias).getPublicKey();
    }

    public static Certificate[] getCertificates(KeyStore ks, String alias) throws Exception {
        return ks.getCertificateChain(alias);
    }

    public static String getAlias(KeyStore ks) throws Exception {
        return ks.aliases().nextElement();
    }

    public static X509Certificate getX509(KeyStore ks, String alias) throws Exception {
        return (X509Certificate) ks.getCertificate(alias);
    }

    @Override
    public String toString() {
        return String.format(
                "CertificateManager{razaoSocial='%s', inscFederal='%s', valid=%s, vcto=%s}",
                razaoSocial, inscFederal, valid, certVcto
        );
    }
}
