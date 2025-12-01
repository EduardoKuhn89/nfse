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
 * <p>Classe Java de TCRTCValoresIBSCBSFed complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCValoresIBSCBSFed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pCBS" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *         &lt;element name="pRedAliqCBS" type="{http://www.sped.fazenda.gov.br/nfse}TSDec3V2" minOccurs="0"/>
 *         &lt;element name="pAliqEfetCBS" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCValoresIBSCBSFed", propOrder = {
    "pcbs",
    "pRedAliqCBS",
    "pAliqEfetCBS"
})
public class TCRTCValoresIBSCBSFed {

    @XmlElement(name = "pCBS", required = true)
    protected String pcbs;
    protected String pRedAliqCBS;
    @XmlElement(required = true)
    protected String pAliqEfetCBS;

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
     * Obtém o valor da propriedade pRedAliqCBS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRedAliqCBS() {
        return pRedAliqCBS;
    }

    /**
     * Define o valor da propriedade pRedAliqCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRedAliqCBS(String value) {
        this.pRedAliqCBS = value;
    }

    /**
     * Obtém o valor da propriedade pAliqEfetCBS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfetCBS() {
        return pAliqEfetCBS;
    }

    /**
     * Define o valor da propriedade pAliqEfetCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqEfetCBS(String value) {
        this.pAliqEfetCBS = value;
    }

}
