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
 * <p>Classe Java de TCRTCTotalIBS complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCTotalIBS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vIBSTot" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *         &lt;element name="gIBSCredPres" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCTotalIBSCredPres" minOccurs="0"/>
 *         &lt;element name="gIBSUFTot" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCTotalIBSUF"/>
 *         &lt;element name="gIBSMunTot" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCTotalIBSMun"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCTotalIBS", propOrder = {
    "vibsTot",
    "gibsCredPres",
    "gibsufTot",
    "gibsMunTot"
})
public class TCRTCTotalIBS {

    @XmlElement(name = "vIBSTot", required = true)
    protected String vibsTot;
    @XmlElement(name = "gIBSCredPres")
    protected TCRTCTotalIBSCredPres gibsCredPres;
    @XmlElement(name = "gIBSUFTot", required = true)
    protected TCRTCTotalIBSUF gibsufTot;
    @XmlElement(name = "gIBSMunTot", required = true)
    protected TCRTCTotalIBSMun gibsMunTot;

    /**
     * Obtém o valor da propriedade vibsTot.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIBSTot() {
        return vibsTot;
    }

    /**
     * Define o valor da propriedade vibsTot.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVIBSTot(String value) {
        this.vibsTot = value;
    }

    /**
     * Obtém o valor da propriedade gibsCredPres.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCTotalIBSCredPres }
     *     
     */
    public TCRTCTotalIBSCredPres getGIBSCredPres() {
        return gibsCredPres;
    }

    /**
     * Define o valor da propriedade gibsCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCTotalIBSCredPres }
     *     
     */
    public void setGIBSCredPres(TCRTCTotalIBSCredPres value) {
        this.gibsCredPres = value;
    }

    /**
     * Obtém o valor da propriedade gibsufTot.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCTotalIBSUF }
     *     
     */
    public TCRTCTotalIBSUF getGIBSUFTot() {
        return gibsufTot;
    }

    /**
     * Define o valor da propriedade gibsufTot.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCTotalIBSUF }
     *     
     */
    public void setGIBSUFTot(TCRTCTotalIBSUF value) {
        this.gibsufTot = value;
    }

    /**
     * Obtém o valor da propriedade gibsMunTot.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCTotalIBSMun }
     *     
     */
    public TCRTCTotalIBSMun getGIBSMunTot() {
        return gibsMunTot;
    }

    /**
     * Define o valor da propriedade gibsMunTot.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCTotalIBSMun }
     *     
     */
    public void setGIBSMunTot(TCRTCTotalIBSMun value) {
        this.gibsMunTot = value;
    }

}
