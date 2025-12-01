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
 * <p>Classe Java de TCRTCListaDocDFe complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCListaDocDFe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoChaveDFe" type="{http://www.sped.fazenda.gov.br/nfse}TSRTCTipoChaveDFe"/>
 *         &lt;element name="xTipoChaveDFe" type="{http://www.sped.fazenda.gov.br/nfse}TSDesc255" minOccurs="0"/>
 *         &lt;element name="chaveDFe" type="{http://www.sped.fazenda.gov.br/nfse}TSRTCChaveDFe"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCListaDocDFe", propOrder = {
    "tipoChaveDFe",
    "xTipoChaveDFe",
    "chaveDFe"
})
public class TCRTCListaDocDFe {

    @XmlElement(required = true)
    protected String tipoChaveDFe;
    protected String xTipoChaveDFe;
    @XmlElement(required = true)
    protected String chaveDFe;

    /**
     * Obtém o valor da propriedade tipoChaveDFe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoChaveDFe() {
        return tipoChaveDFe;
    }

    /**
     * Define o valor da propriedade tipoChaveDFe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoChaveDFe(String value) {
        this.tipoChaveDFe = value;
    }

    /**
     * Obtém o valor da propriedade xTipoChaveDFe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXTipoChaveDFe() {
        return xTipoChaveDFe;
    }

    /**
     * Define o valor da propriedade xTipoChaveDFe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXTipoChaveDFe(String value) {
        this.xTipoChaveDFe = value;
    }

    /**
     * Obtém o valor da propriedade chaveDFe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChaveDFe() {
        return chaveDFe;
    }

    /**
     * Define o valor da propriedade chaveDFe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChaveDFe(String value) {
        this.chaveDFe = value;
    }

}
