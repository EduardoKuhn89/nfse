package br.com.nfse.dto;

import br.com.nfse.interfaces.HttpDataAware;

/**
 *
 * @author eduardo
 */
public class ConvenioResult implements HttpDataAware {

    private HttpResult httpResult;

    private String mensagem;
    private ParametrosConvenio parametrosConvenio;

    public ConvenioResult() {
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ParametrosConvenio getParametrosConvenio() {
        return parametrosConvenio;
    }

    public void setParametrosConvenio(ParametrosConvenio parametrosConvenio) {
        this.parametrosConvenio = parametrosConvenio;
    }

    public HttpResult getHttpResult() {
        return httpResult;
    }

    public void setHttpResult(HttpResult httpResult) {
        this.httpResult = httpResult;
    }

    @Override
    public void setHttpResult(int status, String message, String url, String rawBody) {
        this.httpResult = new HttpResult(status, message, url, rawBody);
    }

    @Override
    public String toString() {
        return "ConvenioResult{" + "mensagem=" + mensagem + '}';
    }

    private class ParametrosConvenio {

        private Integer tipoConvenioDeserializationSetter;
        private Integer aderenteAmbienteNacional;
        private Integer aderenteEmissorNacional;
        private Integer situacaoEmissaoPadraoContribuintesRFB;
        private Integer aderenteMAN;
        private Boolean permiteAproveitametoDeCreditos;

        public ParametrosConvenio() {
        }

        public Integer getTipoConvenioDeserializationSetter() {
            return tipoConvenioDeserializationSetter;
        }

        public void setTipoConvenioDeserializationSetter(Integer tipoConvenioDeserializationSetter) {
            this.tipoConvenioDeserializationSetter = tipoConvenioDeserializationSetter;
        }

        public Integer getAderenteAmbienteNacional() {
            return aderenteAmbienteNacional;
        }

        public void setAderenteAmbienteNacional(Integer aderenteAmbienteNacional) {
            this.aderenteAmbienteNacional = aderenteAmbienteNacional;
        }

        public Integer getAderenteEmissorNacional() {
            return aderenteEmissorNacional;
        }

        public void setAderenteEmissorNacional(Integer aderenteEmissorNacional) {
            this.aderenteEmissorNacional = aderenteEmissorNacional;
        }

        public Integer getSituacaoEmissaoPadraoContribuintesRFB() {
            return situacaoEmissaoPadraoContribuintesRFB;
        }

        public void setSituacaoEmissaoPadraoContribuintesRFB(Integer situacaoEmissaoPadraoContribuintesRFB) {
            this.situacaoEmissaoPadraoContribuintesRFB = situacaoEmissaoPadraoContribuintesRFB;
        }

        public Integer getAderenteMAN() {
            return aderenteMAN;
        }

        public void setAderenteMAN(Integer aderenteMAN) {
            this.aderenteMAN = aderenteMAN;
        }

        public Boolean getPermiteAproveitametoDeCreditos() {
            return permiteAproveitametoDeCreditos;
        }

        public void setPermiteAproveitametoDeCreditos(Boolean permiteAproveitametoDeCreditos) {
            this.permiteAproveitametoDeCreditos = permiteAproveitametoDeCreditos;
        }

        @Override
        public String toString() {
            return "ParametrosConvenio{" + "tipoConvenioDeserializationSetter=" + tipoConvenioDeserializationSetter + '}';
        }

    }
}
