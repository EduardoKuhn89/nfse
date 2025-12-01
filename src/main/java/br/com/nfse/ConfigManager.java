package br.com.nfse;

import br.com.nfse.dto.ProxyConfig;
import br.com.nfse.dto.enuns.AmbienteEnum;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author eduardo
 */
public class ConfigManager {

    public static ConfigManagerBuilder builder() {
        return new ConfigManagerBuilder();
    }

    public static class ConfigManagerBuilder {

        private AmbienteEnum ambiente;
        private CertificateManager certificado;
        private String pathSchemas;
        private ProxyConfig proxy;

        private ConfigManagerBuilder() {
        }

        public ConfigManagerBuilder ambiente(AmbienteEnum ambiente) {
            this.ambiente = Objects.requireNonNull(ambiente, "Ambiente não pode ser nulo");
            return this;
        }

        public ConfigManagerBuilder certificado(CertificateManager certificado) {
            this.certificado = Objects.requireNonNull(certificado, "Certificado não pode ser nulo");
            return this;
        }

        public ConfigManagerBuilder pathSchemas(String pathSchemas) {
            this.pathSchemas = validarPathSchemas(pathSchemas);
            return this;
        }

        public ConfigManagerBuilder proxy(ProxyConfig proxy) {
            this.proxy = proxy;
            return this;
        }

        public ConfigManagerBuilder proxy(String host, Integer port) {
            this.proxy = ProxyConfig.create(host, port);
            return this;
        }

        public ConfigManagerBuilder proxyComAutenticacao(String host, Integer port, String usuario, String senha) {
            this.proxy = ProxyConfig.create(host, port, usuario, senha);
            return this;
        }

        public ConfigManager build() {
            validarConfiguracoesObrigatorias();
            return new ConfigManager(this);
        }

        private void validarConfiguracoesObrigatorias() {
            if (ambiente == null) {
                throw new IllegalStateException("Ambiente é obrigatório");
            }
            if (certificado == null) {
                throw new IllegalStateException("Certificado é obrigatório");
            }
            if (pathSchemas == null || pathSchemas.trim().isEmpty()) {
                throw new IllegalStateException("Path dos schemas é obrigatório");
            }
            if (proxy != null && !proxy.isValid()) {
                throw new IllegalStateException("Configuração de proxy inválida");
            }
        }

        private String validarPathSchemas(String pathSchemas) {
            if (pathSchemas == null || pathSchemas.trim().isEmpty()) {
                throw new IllegalArgumentException("Path dos schemas não pode ser nulo ou vazio");
            }

            Path path = Paths.get(pathSchemas);
            if (!Files.exists(path)) {
                throw new IllegalArgumentException("Diretório de schemas não existe: " + pathSchemas);
            }
            if (!Files.isDirectory(path)) {
                throw new IllegalArgumentException("Path dos schemas deve ser um diretório: " + pathSchemas);
            }
            if (!Files.isReadable(path)) {
                throw new IllegalArgumentException("Sem permissão de leitura no diretório de schemas: " + pathSchemas);
            }

            return pathSchemas;
        }

        public AmbienteEnum getAmbiente() {
            return ambiente;
        }

        public CertificateManager getCertificado() {
            return certificado;
        }

        public String getPathSchemas() {
            return pathSchemas;
        }

        public ProxyConfig getProxy() {
            return proxy;
        }

        public boolean temProxy() {
            return proxy != null && proxy.isValid();
        }
    }

    private final AmbienteEnum ambiente;
    private final CertificateManager certificado;
    private final String pathSchemas;
    private final ProxyConfig proxy;

    private ConfigManager(ConfigManagerBuilder builder) {
        this.ambiente = builder.ambiente;
        this.certificado = builder.certificado;
        this.pathSchemas = builder.pathSchemas;
        this.proxy = builder.proxy;
    }

    public AmbienteEnum getAmbiente() {
        return ambiente;
    }

    public CertificateManager getCertificado() {
        return certificado;
    }

    public String getPathSchemas() {
        return pathSchemas;
    }

    public ProxyConfig getProxy() {
        return proxy;
    }

    public boolean temProxy() {
        return proxy != null && proxy.isValid();
    }

    public boolean temAutenticacaoProxy() {
        return temProxy() && proxy.temAutenticacao();
    }

    @Override
    public String toString() {
        return String.format(
                "ConfigManager{ambiente=%s, pathSchemas='%s', proxy=%s}", ambiente, pathSchemas, proxy
        );
    }

}
