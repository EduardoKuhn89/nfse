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
 * <p>Classe Java de TCRTCTotalTribCompraGov complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCTotalTribCompraGov">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pIBSUF" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *         &lt;element name="vIBSUF" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *         &lt;element name="pIBSMun" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *         &lt;element name="vIBSMun" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *         &lt;element name="pCBS" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *         &lt;element name="vCBS" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCTotalTribCompraGov", propOrder = {
    "pibsuf",
    "vibsuf",
    "pibsMun",
    "vibsMun",
    "pcbs",
    "vcbs"
})
public class TCRTCTotalTribCompraGov {

    @XmlElement(name = "pIBSUF", required = true)
    protected String pibsuf;
    @XmlElement(name = "vIBSUF", required = true)
    protected String vibsuf;
    @XmlElement(name = "pIBSMun", required = true)
    protected String pibsMun;
    @XmlElement(name = "vIBSMun", required = true)
    protected String vibsMun;
    @XmlElement(name = "pCBS", required = true)
    protected String pcbs;
    @XmlElement(name = "vCBS", required = true)
    protected String vcbs;

    /**
     * Obtém o valor da propriedade pibsuf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPIBSUF() {
        return pibsuf;
    }

    /**
     * Define o valor da propriedade pibsuf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPIBSUF(String value) {
        this.pibsuf = value;
    }

    /**
     * Obtém o valor da propriedade vibsuf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIBSUF() {
        return vibsuf;
    }

    /**
     * Define o valor da propriedade vibsuf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVIBSUF(String value) {
        this.vibsuf = value;
    }

    /**
     * Obtém o valor da propriedade pibsMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPIBSMun() {
        return pibsMun;
    }

    /**
     * Define o valor da propriedade pibsMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPIBSMun(String value) {
        this.pibsMun = value;
    }

    /**
     * Obtém o valor da propriedade vibsMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIBSMun() {
        return vibsMun;
    }

    /**
     * Define o valor da propriedade vibsMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVIBSMun(String value) {
        this.vibsMun = value;
    }

    /**
     * Obtém o valor da propriedade pcbs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCBS() {
        return pcbs;
    }

    /**
     * Define o valor da propriedade pcbs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCBS(String value) {
        this.pcbs = value;
    }

    /**
     * Obtém o valor da propriedade vcbs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCBS() {
        return vcbs;
    }

    /**
     * Define o valor da propriedade vcbs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCBS(String value) {
        this.vcbs = value;
    }

}
