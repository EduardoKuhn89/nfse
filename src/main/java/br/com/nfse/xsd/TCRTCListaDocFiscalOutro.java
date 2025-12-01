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
 * <p>Classe Java de TCRTCListaDocFiscalOutro complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCListaDocFiscalOutro">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cMunDocFiscal" type="{http://www.sped.fazenda.gov.br/nfse}TSNum7Dig"/>
 *         &lt;element name="nDocFiscal" type="{http://www.sped.fazenda.gov.br/nfse}TSDesc255"/>
 *         &lt;element name="xDocFiscal" type="{http://www.sped.fazenda.gov.br/nfse}TSDesc255"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCListaDocFiscalOutro", propOrder = {
    "cMunDocFiscal",
    "nDocFiscal",
    "xDocFiscal"
})
public class TCRTCListaDocFiscalOutro {

    @XmlElement(required = true)
    protected String cMunDocFiscal;
    @XmlElement(required = true)
    protected String nDocFiscal;
    @XmlElement(required = true)
    protected String xDocFiscal;

    /**
     * Obtém o valor da propriedade cMunDocFiscal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCMunDocFiscal() {
        return cMunDocFiscal;
    }

    /**
     * Define o valor da propriedade cMunDocFiscal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCMunDocFiscal(String value) {
        this.cMunDocFiscal = value;
    }

    /**
     * Obtém o valor da propriedade nDocFiscal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNDocFiscal() {
        return nDocFiscal;
    }

    /**
     * Define o valor da propriedade nDocFiscal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNDocFiscal(String value) {
        this.nDocFiscal = value;
    }

    /**
     * Obtém o valor da propriedade xDocFiscal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXDocFiscal() {
        return xDocFiscal;
    }

    /**
     * Define o valor da propriedade xDocFiscal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXDocFiscal(String value) {
        this.xDocFiscal = value;
    }

}
