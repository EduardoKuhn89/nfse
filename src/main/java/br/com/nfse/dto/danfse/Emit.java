package br.com.nfse.dto.danfse;

import javax.xml.bind.annotation.*;

/**
 * Dados do emitente (município gerador da NFS-e).
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Emit {

    @XmlElement(name = "CNPJ", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String cnpj;

    @XmlElement(name = "CPF", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String cpf;

    @XmlElement(name = "IM", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String im;

    @XmlElement(name = "xNome", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String xNome;

    @XmlElement(name = "enderNac", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private EnderNac enderNac;

    @XmlElement(name = "fone", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String fone;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getxNome() {
        return xNome;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public EnderNac getEnderNac() {
        return enderNac;
    }

    public void setEnderNac(EnderNac enderNac) {
        this.enderNac = enderNac;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getInscricaoFederal() {
        return cnpj != null ? cnpj : cpf;
    }
}
