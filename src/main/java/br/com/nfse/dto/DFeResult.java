package br.com.nfse.dto;

import com.google.gson.annotations.SerializedName;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
  Objeto de retorno da API de Distribuição de DF-e (ADN NFS-e).

  Encapsula tanto os dados da resposta HTTP quanto o conteúdo deserializado do
  JSON retornado pelo Ambiente de Dados Nacional.
 */
public class DFeResult {

    @SerializedName("StatusProcessamento")
    private String statusProcessamento;

    @SerializedName("LoteDFe")
    private List<DFeItem> loteDFe;

    @SerializedName("Alertas")
    private List<DFeMensagem> alertas;

    @SerializedName("Erros")
    private List<DFeMensagem> erros;

    @SerializedName("TipoAmbiente")
    private String tipoAmbiente;

    @SerializedName("VersaoAplicativo")
    private String versaoAplicativo;

    @SerializedName("DataHoraProcessamento")
    private String dataHoraProcessamento;

    private transient boolean successful;

    private transient int httpCode;

    private transient String httpMessage;

    private transient String url;

    private transient String jsonData;

    public String getStatusProcessamento() {
        return statusProcessamento;
    }

    public void setStatusProcessamento(String statusProcessamento) {
        this.statusProcessamento = statusProcessamento;
    }

    public String getMensagemProcessamento() {
        if (statusProcessamento == null) {
            return "Sem Status de Retorno";
        }

        switch (statusProcessamento) {
            case "NENHUM_DOCUMENTO_LOCALIZADO":
                return "Nenhum documento localizado";
            case "DOCUMENTOS_LOCALIZADOS":
                return "Documento(s) localizado(s)";
            case "REJEICAO":
                return "Rejeição";
            default:
                throw new AssertionError();
        }
    }

    public String getCodigoProcessamento() {
        if (statusProcessamento == null) {
            return "0";
        }

        switch (statusProcessamento) {
            case "NENHUM_DOCUMENTO_LOCALIZADO":
                return "137";
            case "DOCUMENTOS_LOCALIZADOS":
                return "138";
            case "REJEICAO":
                return "139";
            default:
                throw new AssertionError();
        }
    }

    public List<DFeItem> getLoteDFe() {
        return loteDFe;
    }

    public void setLoteDFe(List<DFeItem> loteDFe) {
        this.loteDFe = loteDFe;
    }

    public List<DFeMensagem> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<DFeMensagem> alertas) {
        this.alertas = alertas;
    }

    public List<DFeMensagem> getErros() {
        return erros;
    }

    public void setErros(List<DFeMensagem> erros) {
        this.erros = erros;
    }

    public String getTipoAmbiente() {
        return tipoAmbiente;
    }

    public void setTipoAmbiente(String tipoAmbiente) {
        this.tipoAmbiente = tipoAmbiente;
    }

    public String getVersaoAplicativo() {
        return versaoAplicativo;
    }

    public void setVersaoAplicativo(String versaoAplicativo) {
        this.versaoAplicativo = versaoAplicativo;
    }

    public String getDataHoraProcessamento() {
        return dataHoraProcessamento;
    }

    public void setDataHoraProcessamento(String dataHoraProcessamento) {
        this.dataHoraProcessamento = dataHoraProcessamento;
    }

    public OffsetDateTime getDataHoraProcessamentoParsed() {
        if (dataHoraProcessamento == null || dataHoraProcessamento.isEmpty()) {
            return null;
        }
        try {
            return OffsetDateTime.parse(dataHoraProcessamento, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getHttpMessage() {
        return httpMessage;
    }

    public void setHttpMessage(String httpMessage) {
        this.httpMessage = httpMessage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public boolean temDocumentos() {
        return "DOCUMENTOS_LOCALIZADOS".equalsIgnoreCase(statusProcessamento)
                && loteDFe != null
                && !loteDFe.isEmpty();
    }

    public boolean semDocumentos() {
        return "NENHUM_DOCUMENTO_LOCALIZADO".equalsIgnoreCase(statusProcessamento);
    }

    public boolean isRejeicao() {
        return "REJEICAO".equalsIgnoreCase(statusProcessamento);
    }

    public long getUltimoNSU() {
        if (loteDFe == null || loteDFe.isEmpty()) {
            return 0L;
        }
        return loteDFe.stream()
                .map(DFeItem::getNsu)
                .filter(nsu -> nsu != null)
                .mapToLong(Long::longValue)
                .max()
                .orElse(0L);
    }

    @Override
    public String toString() {
        return "DFeResult{"
                + "httpCode=" + httpCode
                + ", status='" + statusProcessamento + '\''
                + ", documentos=" + (loteDFe != null ? loteDFe.size() : 0)
                + ", ambiente='" + tipoAmbiente + '\''
                + '}';
    }
}
