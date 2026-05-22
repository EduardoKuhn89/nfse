package br.com.nfse.dto.danfse;

import javax.xml.bind.annotation.*;

/**
 * Elemento {@code <infNFSe>} – informações gerais da NFS-e autorizada.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class InfNFSe {

    @XmlAttribute(name = "Id")
    private String id;

    /**
     * Município de emissão.
     */
    @XmlElement(name = "xLocEmi", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String xLocEmi;

    /**
     * Município de prestação do serviço.
     */
    @XmlElement(name = "xLocPrestacao", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String xLocPrestacao;

    /**
     * Número da NFS-e.
     */
    @XmlElement(name = "nNFSe", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String nNFSe;

    /**
     * Código do município de incidência.
     */
    @XmlElement(name = "cLocIncid", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String cLocIncid;

    /**
     * Município de incidência.
     */
    @XmlElement(name = "xLocIncid", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String xLocIncid;

    /**
     * Descrição da tributação nacional.
     */
    @XmlElement(name = "xTribNac", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String xTribNac;

    /**
     * Versão do aplicativo.
     */
    @XmlElement(name = "verAplic", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String verAplic;

    /**
     * Ambiente gerador: 1=Produção, 2=Homologação.
     */
    @XmlElement(name = "ambGer", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String ambGer;

    /**
     * Tipo de emissão.
     */
    @XmlElement(name = "tpEmis", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String tpEmis;

    /**
     * Código de status (100 = autorizado).
     */
    @XmlElement(name = "cStat", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String cStat;

    /**
     * Data/hora do processamento (dhProc = Data e Hora da emissão da NFS-e).
     */
    @XmlElement(name = "dhProc", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String dhProc;

    /**
     * Número do DF-e.
     */
    @XmlElement(name = "nDFSe", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String nDFSe;

    /**
     * Dados do emitente (município emissor).
     */
    @XmlElement(name = "emit", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private Emit emit;

    /**
     * Valores totais da NFS-e.
     */
    @XmlElement(name = "valores", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private ValoresNFSe valores;

    /**
     * Bloco IBS/CBS da NFS-e.
     */
    @XmlElement(name = "IBSCBS", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private IbsCbsNFSe ibsCbs;

    /**
     * DPS embutida na NFS-e.
     */
    @XmlElement(name = "DPS", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private DPS dps;

    public String getId() {
        return id;
    }

    public String getChNFSe() {
        return id != null ? id.replace("NFS", "") : null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getxLocEmi() {
        return xLocEmi;
    }

    public void setxLocEmi(String xLocEmi) {
        this.xLocEmi = xLocEmi;
    }

    public String getxLocPrestacao() {
        return xLocPrestacao;
    }

    public void setxLocPrestacao(String xLocPrestacao) {
        this.xLocPrestacao = xLocPrestacao;
    }

    public String getnNFSe() {
        return nNFSe;
    }

    public void setnNFSe(String nNFSe) {
        this.nNFSe = nNFSe;
    }

    public String getcLocIncid() {
        return cLocIncid;
    }

    public void setcLocIncid(String cLocIncid) {
        this.cLocIncid = cLocIncid;
    }

    public String getxLocIncid() {
        return xLocIncid;
    }

    public void setxLocIncid(String xLocIncid) {
        this.xLocIncid = xLocIncid;
    }

    public String getxTribNac() {
        return xTribNac;
    }

    public void setxTribNac(String xTribNac) {
        this.xTribNac = xTribNac;
    }

    public String getVerAplic() {
        return verAplic;
    }

    public void setVerAplic(String verAplic) {
        this.verAplic = verAplic;
    }

    public String getAmbGer() {
        return ambGer;
    }

    public void setAmbGer(String ambGer) {
        this.ambGer = ambGer;
    }

    public String getTpEmis() {
        return tpEmis;
    }

    public void setTpEmis(String tpEmis) {
        this.tpEmis = tpEmis;
    }

    public String getcStat() {
        return cStat;
    }

    public void setcStat(String cStat) {
        this.cStat = cStat;
    }

    public String getDhProc() {
        return dhProc;
    }

    public void setDhProc(String dhProc) {
        this.dhProc = dhProc;
    }

    public String getnDFSe() {
        return nDFSe;
    }

    public void setnDFSe(String nDFSe) {
        this.nDFSe = nDFSe;
    }

    public Emit getEmit() {
        return emit;
    }

    public void setEmit(Emit emit) {
        this.emit = emit;
    }

    public ValoresNFSe getValores() {
        return valores;
    }

    public void setValores(ValoresNFSe valores) {
        this.valores = valores;
    }

    public IbsCbsNFSe getIbsCbs() {
        return ibsCbs;
    }

    public void setIbsCbs(IbsCbsNFSe ibsCbs) {
        this.ibsCbs = ibsCbs;
    }

    public DPS getDps() {
        return dps;
    }

    public void setDps(DPS dps) {
        this.dps = dps;
    }

    public String getXAmbGer() {
        if (ambGer == null) {
            return "";
        }

        switch (ambGer) {
            case "1":
                return "Próprio do Município";
            case "2":
                return "Sefin Nacional NFS";
            default:
                return "Outro";
        }
    }

}
