package br.com.nfse.dto;

import br.com.nfse.interfaces.HttpDataAware;
import br.com.nfse.utils.XmlUtils;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class EventoResult implements HttpDataAware {

    private boolean successful;

    private HttpResult httpResult;

    private Integer tipoAmbiente;
    private String versaoAplicativo;
    private ZonedDateTime dataHoraProcessamento;

    private String eventoXmlGZipB64;
    private String eventoXml;

    private List<Erros> erro;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public HttpResult getHttpResult() {
        return httpResult;
    }

    public void setHttpResult(HttpResult httpResult) {
        this.httpResult = httpResult;
    }

    public Integer getTipoAmbiente() {
        return tipoAmbiente;
    }

    public void setTipoAmbiente(Integer tipoAmbiente) {
        this.tipoAmbiente = tipoAmbiente;
    }

    public String getVersaoAplicativo() {
        return versaoAplicativo;
    }

    public void setVersaoAplicativo(String versaoAplicativo) {
        this.versaoAplicativo = versaoAplicativo;
    }

    public ZonedDateTime getDataHoraProcessamento() {
        return dataHoraProcessamento;
    }

    public void setDataHoraProcessamento(ZonedDateTime dataHoraProcessamento) {
        this.dataHoraProcessamento = dataHoraProcessamento;
    }

    public String getEventoXmlGZipB64() {
        return eventoXmlGZipB64;
    }

    public void setEventoXmlGZipB64(String eventoXmlGZipB64) {
        this.eventoXmlGZipB64 = eventoXmlGZipB64;
    }

    public List<Erros> getErro() {
        return erro;
    }

    public void setErro(List<Erros> erro) {
        this.erro = erro;
    }

    public String getEventoXml() throws IOException {
        if (eventoXml == null) {
            if (eventoXmlGZipB64 != null && !eventoXmlGZipB64.isEmpty()) {
                eventoXml = XmlUtils.gZipB64ToXml(eventoXmlGZipB64);
            }
        }
        return eventoXml;
    }

    @Override
    public void setHttpResult(int status, String message, String url, String rawBody) {
        this.httpResult = new HttpResult(status, message, url, rawBody);
    }

    @Override
    public String toString() {
        return "EventoResult{" + "successful=" + successful + ", tipoAmbiente=" + tipoAmbiente + ", versaoAplicativo=" + versaoAplicativo + '}';
    }

    private class Erros {

        private String codigo;
        private String mensagem;
        private String descricao;
        private String complemento;

        public Erros() {
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public String getComplemento() {
            return complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }

        @Override
        public String toString() {
            return "Erros{" + "codigo=" + codigo + ", descricao=" + descricao + ", complemento=" + complemento + '}';
        }

    }
}
