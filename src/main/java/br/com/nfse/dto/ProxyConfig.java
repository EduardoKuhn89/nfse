package br.com.nfse.dto;

import java.util.Objects;

/**
 *
 * @author eduardo
 */
public class ProxyConfig {

    private final String host;
    private final Integer port;
    private final String usuario;
    private final String senha;

    private ProxyConfig(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.usuario = builder.usuario;
        this.senha = builder.senha;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static ProxyConfig create(String host, Integer port) {
        return builder().host(host).port(port).build();
    }

    public static ProxyConfig create(String host, Integer port, String usuario, String senha) {
        return builder().host(host).port(port).usuario(usuario).senha(senha).build();
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public boolean temAutenticacao() {
        return usuario != null && senha != null;
    }

    public boolean isValid() {
        return host != null && !host.trim().isEmpty() && port != null && port > 0 && port <= 65535;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProxyConfig that = (ProxyConfig) o;
        return Objects.equals(host, that.host)
                && Objects.equals(port, that.port)
                && Objects.equals(usuario, that.usuario)
                && Objects.equals(senha, that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port, usuario, senha);
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "ProxyConfig{INVALIDO}";
        }
        if (temAutenticacao()) {
            return String.format("ProxyConfig{host='%s', port=%d, usuario='%s'}", host, port, usuario);
        }
        return String.format("ProxyConfig{host='%s', port=%d}", host, port);
    }

    public static class Builder {

        private String host;
        private Integer port;
        private String usuario;
        private String senha;

        public Builder host(String host) {
            this.host = Objects.requireNonNull(host, "Host do proxy não pode ser nulo").trim();
            if (this.host.isEmpty()) {
                throw new IllegalArgumentException("Host do proxy não pode ser vazio");
            }
            return this;
        }

        public Builder port(Integer port) {
            if (port == null || port <= 0 || port > 65535) {
                throw new IllegalArgumentException("Porta do proxy deve estar entre 1 e 65535");
            }
            this.port = port;
            return this;
        }

        public Builder usuario(String usuario) {
            this.usuario = usuario;
            return this;
        }

        public Builder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public ProxyConfig build() {
            if (host == null || port == null) {
                throw new IllegalStateException("Host e porta são obrigatórios para configuração de proxy");
            }

            if ((usuario != null && senha == null) || (usuario == null && senha != null)) {
                throw new IllegalStateException("Usuário e senha devem ser fornecidos juntos para autenticação de proxy");
            }
            return new ProxyConfig(this);
        }
    }
}
