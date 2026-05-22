package br.com.nfse.dto;

import com.google.gson.annotations.SerializedName;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/*
  Representa um único Documento Fiscal Eletrônico (DF-e) dentro do lote de
  distribuição.
 */
public class DFeItem {

    @SerializedName("NSU")
    private Long nsu;

    @SerializedName("ChaveAcesso")
    private String chaveAcesso;

    @SerializedName("TipoDocumento")
    private String tipoDocumento;

    @SerializedName("TipoEvento")
    private String tipoEvento;

    @SerializedName("ArquivoXml")
    private String arquivoXml;

    @SerializedName("DataHoraGeracao")
    private String dataHoraGeracao;

    private transient String xml;

    public Long getNsu() {
        return nsu;
    }

    public void setNsu(Long nsu) {
        this.nsu = nsu;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getArquivoXml() {
        return arquivoXml;
    }

    public void setArquivoXml(String arquivoXml) {
        this.arquivoXml = arquivoXml;
    }

    public String getDataHoraGeracao() {
        return dataHoraGeracao;
    }

    public void setDataHoraGeracao(String dataHoraGeracao) {
        this.dataHoraGeracao = dataHoraGeracao;
    }

    public OffsetDateTime getDataHoraGeracaoParsed() {
        if (dataHoraGeracao == null || dataHoraGeracao.isEmpty()) {
            return null;
        }
        try {
            return OffsetDateTime.parse(dataHoraGeracao, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        } catch (Exception e) {
            return null;
        }
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public boolean isNFSe() {
        return "NFSE".equalsIgnoreCase(tipoDocumento);
    }

    public boolean isEvento() {
        return "EVENTO".equalsIgnoreCase(tipoDocumento);
    }

    public boolean isXmlDisponivel() {
        return xml != null && !xml.isEmpty();
    }

    @Override
    public String toString() {
        return "DFeItem{"
                + "nsu=" + nsu
                + ", chaveAcesso='" + chaveAcesso + '\''
                + ", tipoDocumento='" + tipoDocumento + '\''
                + ", tipoEvento='" + tipoEvento + '\''
                + ", dataHoraGeracao=" + dataHoraGeracao
                + ", xmlDisponivel=" + isXmlDisponivel()
                + '}';
    }
}
