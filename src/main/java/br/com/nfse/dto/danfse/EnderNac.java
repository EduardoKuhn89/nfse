package br.com.nfse.dto.danfse;

import javax.xml.bind.annotation.*;

/**
 * Endereço nacional.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EnderNac {

    @XmlElement(name = "xLgr", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String xLgr;

    @XmlElement(name = "nro", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String nro;

    @XmlElement(name = "xCpl", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String xCpl;

    @XmlElement(name = "xBairro", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String xBairro;

    @XmlElement(name = "cMun", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String cMun;

    @XmlElement(name = "UF", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String uf;

    @XmlElement(name = "CEP", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String cep;

    public String getxLgr() {
        return xLgr;
    }

    public void setxLgr(String xLgr) {
        this.xLgr = xLgr;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getxCpl() {
        return xCpl;
    }

    public void setxCpl(String xCpl) {
        this.xCpl = xCpl;
    }

    public String getxBairro() {
        return xBairro;
    }

    public void setxBairro(String xBairro) {
        this.xBairro = xBairro;
    }

    public String getcMun() {
        return cMun;
    }

    public void setcMun(String cMun) {
        this.cMun = cMun;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Endereço formatado numa linha.
     *
     * @return
     */
    public String enderecoCompleto() {
        StringBuilder sb = new StringBuilder();
        if (xLgr != null) {
            sb.append(xLgr);
        }
        if (nro != null) {
            sb.append(", ").append(nro);
        }
        if (xCpl != null && !xCpl.isEmpty()) {
            sb.append(", ").append(xCpl);
        }
        if (xBairro != null) {
            sb.append(", ").append(xBairro);
        }
        return sb.toString();
    }
}
