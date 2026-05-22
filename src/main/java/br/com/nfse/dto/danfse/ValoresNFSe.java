package br.com.nfse.dto.danfse;

import javax.xml.bind.annotation.*;

/**
 * Valores totais no nível {@code <NFSe><valores>}.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ValoresNFSe {

    /**
     * Valor líquido da NFS-e.
     */
    @XmlElement(name = "vLiq", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String vLiq;

    public String getvLiq() {
        return vLiq;
    }

    public void setvLiq(String vLiq) {
        this.vLiq = vLiq;
    }
}
