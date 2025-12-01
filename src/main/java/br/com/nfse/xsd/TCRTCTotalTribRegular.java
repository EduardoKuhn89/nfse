//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2025.11.28 às 01:51:46 PM BRT 
//


package br.com.nfse.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de TCRTCTotalTribRegular complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCTotalTribRegular">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pAliqEfeRegIBSUF" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *         &lt;element name="vTribRegIBSUF" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *         &lt;element name="pAliqEfeRegIBSMun" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *         &lt;element name="vTribRegIBSMun" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *         &lt;element name="pAliqEfeRegCBS" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *         &lt;element name="vTribRegCBS" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCTotalTribRegular", propOrder = {
    "pAliqEfeRegIBSUF",
    "vTribRegIBSUF",
    "pAliqEfeRegIBSMun",
    "vTribRegIBSMun",
    "pAliqEfeRegCBS",
    "vTribRegCBS"
})
public class TCRTCTotalTribRegular {

    @XmlElement(required = true)
    protected String pAliqEfeRegIBSUF;
    @XmlElement(required = true)
    protected String vTribRegIBSUF;
    @XmlElement(required = true)
    protected String pAliqEfeRegIBSMun;
    @XmlElement(required = true)
    protected String vTribRegIBSMun;
    @XmlElement(required = true)
    protected String pAliqEfeRegCBS;
    @XmlElement(required = true)
    protected String vTribRegCBS;

    /**
     * Obtém o valor da propriedade pAliqEfeRegIBSUF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfeRegIBSUF() {
        return pAliqEfeRegIBSUF;
    }

    /**
     * Define o valor da propriedade pAliqEfeRegIBSUF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqEfeRegIBSUF(String value) {
        this.pAliqEfeRegIBSUF = value;
    }

    /**
     * Obtém o valor da propriedade vTribRegIBSUF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribRegIBSUF() {
        return vTribRegIBSUF;
    }

    /**
     * Define o valor da propriedade vTribRegIBSUF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTribRegIBSUF(String value) {
        this.vTribRegIBSUF = value;
    }

    /**
     * Obtém o valor da propriedade pAliqEfeRegIBSMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfeRegIBSMun() {
        return pAliqEfeRegIBSMun;
    }

    /**
     * Define o valor da propriedade pAliqEfeRegIBSMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqEfeRegIBSMun(String value) {
        this.pAliqEfeRegIBSMun = value;
    }

    /**
     * Obtém o valor da propriedade vTribRegIBSMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribRegIBSMun() {
        return vTribRegIBSMun;
    }

    /**
     * Define o valor da propriedade vTribRegIBSMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTribRegIBSMun(String value) {
        this.vTribRegIBSMun = value;
    }

    /**
     * Obtém o valor da propriedade pAliqEfeRegCBS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfeRegCBS() {
        return pAliqEfeRegCBS;
    }

    /**
     * Define o valor da propriedade pAliqEfeRegCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqEfeRegCBS(String value) {
        this.pAliqEfeRegCBS = value;
    }

    /**
     * Obtém o valor da propriedade vTribRegCBS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribRegCBS() {
        return vTribRegCBS;
    }

    /**
     * Define o valor da propriedade vTribRegCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTribRegCBS(String value) {
        this.vTribRegCBS = value;
    }

}
