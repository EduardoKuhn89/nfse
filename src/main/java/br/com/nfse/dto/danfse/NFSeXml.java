package br.com.nfse.dto.danfse;

import javax.xml.bind.annotation.*;

/**
 * Raiz do XML da NFS-e (elemento {@code <NFSe>}).
 */
@XmlRootElement(name = "NFSe", namespace = "http://www.sped.fazenda.gov.br/nfse")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFSeXml {

    @XmlAttribute(name = "versao")
    private String versao;

    @XmlElement(name = "infNFSe", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private InfNFSe infNFSe;

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public InfNFSe getInfNFSe() {
        return infNFSe;
    }

    public void setInfNFSe(InfNFSe infNFSe) {
        this.infNFSe = infNFSe;
    }
}
