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
 * <p>Classe Java de TCRTCValoresIBSCBSUF complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCValoresIBSCBSUF">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pIBSUF" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *         &lt;element name="pRedAliqUF" type="{http://www.sped.fazenda.gov.br/nfse}TSDec3V2" minOccurs="0"/>
 *         &lt;element name="pAliqEfetUF" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCValoresIBSCBSUF", propOrder = {
    "pibsuf",
    "pRedAliqUF",
    "pAliqEfetUF"
})
public class TCRTCValoresIBSCBSUF {

    @XmlElement(name = "pIBSUF", required = true)
    protected String pibsuf;
    protected String pRedAliqUF;
    @XmlElement(required = true)
    protected String pAliqEfetUF;

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
     * Obtém o valor da propriedade pRedAliqUF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRedAliqUF() {
        return pRedAliqUF;
    }

    /**
     * Define o valor da propriedade pRedAliqUF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRedAliqUF(String value) {
        this.pRedAliqUF = value;
    }

    /**
     * Obtém o valor da propriedade pAliqEfetUF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfetUF() {
        return pAliqEfetUF;
    }

    /**
     * Define o valor da propriedade pAliqEfetUF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqEfetUF(String value) {
        this.pAliqEfetUF = value;
    }

}
