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
public class NFSeResult implements HttpDataAware {

    private boolean successful;

    private HttpResult httpResult;

    private Integer tipoAmbiente;
    private String versaoAplicativo;
    private ZonedDateTime dataHoraProcessamento;
    private String idDPS;
    private String chaveAcesso;

    private String dpsXml;

    private String nfseXmlGZipB64;
    private String nfseXml;

    private List<Alertas> alertas;
    private List<Erros> erros;

    public NFSeResult() {
    }

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

    public String getIdDPS() {
        return idDPS;
    }

    public void setIdDPS(String idDPS) {
        this.idDPS = idDPS;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public String getDpsXml() {
        return dpsXml;
    }

    public void setDpsXml(String dpsXml) {
        this.dpsXml = dpsXml;
    }

    public List<Alertas> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alertas> alertas) {
        this.alertas = alertas;
    }

    public List<Erros> getErros() {
        return erros;
    }

    public void setErros(List<Erros> erros) {
        this.erros = erros;
    }

    public String getNfseXmlGZipB64() {
        return nfseXmlGZipB64;
    }

    public void setNfseXmlGZipB64(String nfseXmlGZipB64) {
        this.nfseXmlGZipB64 = nfseXmlGZipB64;
    }

    public String getNfseXml() throws IOException {
        if (nfseXml == null) {
            if (nfseXmlGZipB64 != null && !nfseXmlGZipB64.isEmpty()) {
                nfseXml = XmlUtils.gZipB64ToXml(nfseXmlGZipB64);
            }
        }
        return nfseXml;
    }

    @Override
    public void setHttpResult(int status, String message, String url, String rawBody) {
        this.httpResult = new HttpResult(status, message, url, rawBody);
    }

    @Override
    public String toString() {
        return "NFSeResult{" + "successful=" + successful + ", tipoAmbiente=" + tipoAmbiente + ", versaoAplicativo=" + versaoAplicativo + ", idDPS=" + idDPS + ", chaveAcesso=" + chaveAcesso + '}';
    }

    private class Alertas {

        private String codigo;
        private String mensagem;
        private String descricao;
        private String complemento;

        public Alertas() {
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
            return "Alertas{" + "codigo=" + codigo + ", mensagem=" + mensagem + '}';
        }

    }

    private class Erros {

        private String Codigo;
        private String Descricao;
        private String Complemento;

        public Erros() {
        }

        public String getCodigo() {
            return Codigo;
        }

        public void setCodigo(String Codigo) {
            this.Codigo = Codigo;
        }

        public String getDescricao() {
            return Descricao;
        }

        public void setDescricao(String Descricao) {
            this.Descricao = Descricao;
        }

        public String getComplemento() {
            return Complemento;
        }

        public void setComplemento(String Complemento) {
            this.Complemento = Complemento;
        }

        @Override
        public String toString() {
            return "Erros{" + "Codigo=" + Codigo + ", Descricao=" + Descricao + ", Complemento=" + Complemento + '}';
        }

    }

}
