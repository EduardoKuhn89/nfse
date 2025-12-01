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
 * <p>Classe Java de TCRTCValoresIBSCBSMun complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCValoresIBSCBSMun">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pIBSMun" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *         &lt;element name="pRedAliqMun" type="{http://www.sped.fazenda.gov.br/nfse}TSDec3V2" minOccurs="0"/>
 *         &lt;element name="pAliqEfetMun" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCValoresIBSCBSMun", propOrder = {
    "pibsMun",
    "pRedAliqMun",
    "pAliqEfetMun"
})
public class TCRTCValoresIBSCBSMun {

    @XmlElement(name = "pIBSMun", required = true)
    protected String pibsMun;
    protected String pRedAliqMun;
    @XmlElement(required = true)
    protected String pAliqEfetMun;

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
     * Obtém o valor da propriedade pRedAliqMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRedAliqMun() {
        return pRedAliqMun;
    }

    /**
     * Define o valor da propriedade pRedAliqMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRedAliqMun(String value) {
        this.pRedAliqMun = value;
    }

    /**
     * Obtém o valor da propriedade pAliqEfetMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfetMun() {
        return pAliqEfetMun;
    }

    /**
     * Define o valor da propriedade pAliqEfetMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqEfetMun(String value) {
        this.pAliqEfetMun = value;
    }

}
