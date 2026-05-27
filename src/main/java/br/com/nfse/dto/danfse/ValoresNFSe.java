package br.com.nfse.dto.danfse;

import javax.xml.bind.annotation.*;

/**
 * Valores totais no nível {@code <NFSe><valores>}.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ValoresNFSe {

    @XmlElement(name = "vCalcDR", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String vCalcDR;

    @XmlElement(name = "vBC", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String vBC;

    @XmlElement(name = "pAliqAplic", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String pAliqAplic;

    @XmlElement(name = "vISSQN", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String vISSQN;

    @XmlElement(name = "vTotalRet", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String vTotalRet;

    /**
     * Valor líquido da NFS-e.
     */
    @XmlElement(name = "vLiq", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String vLiq;

    public String getvCalcDR() {
        return vCalcDR;
    }

    public void setvCalcDR(String vCalcDR) {
        this.vCalcDR = vCalcDR;
    }

    public String getvBC() {
        return vBC;
    }

    public void setvBC(String vBC) {
        this.vBC = vBC;
    }

    public String getpAliqAplic() {
        return pAliqAplic;
    }

    public void setpAliqAplic(String pAliqAplic) {
        this.pAliqAplic = pAliqAplic;
    }

    public String getvISSQN() {
        return vISSQN;
    }

    public void setvISSQN(String vISSQN) {
        this.vISSQN = vISSQN;
    }

    public String getvTotalRet() {
        return vTotalRet;
    }

    public void setvTotalRet(String vTotalRet) {
        this.vTotalRet = vTotalRet;
    }

    public String getvLiq() {
        return vLiq;
    }

    public void setvLiq(String vLiq) {
        this.vLiq = vLiq;
    }
}
