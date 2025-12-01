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
 * <p>Classe Java de TCRTCInfoTributosSitClas complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCInfoTributosSitClas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CST" type="{http://www.sped.fazenda.gov.br/nfse}TSRTCCodSitTrib"/>
 *         &lt;element name="cClassTrib" type="{http://www.sped.fazenda.gov.br/nfse}TSRTCCodClassTrib"/>
 *         &lt;element name="cCredPres" type="{http://www.sped.fazenda.gov.br/nfse}TSRTCCodCredPres" minOccurs="0"/>
 *         &lt;element name="gTribRegular" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCInfoTributosTribRegular" minOccurs="0"/>
 *         &lt;element name="gDif" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCInfoTributosDif" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCInfoTributosSitClas", propOrder = {
    "cst",
    "cClassTrib",
    "cCredPres",
    "gTribRegular",
    "gDif"
})
public class TCRTCInfoTributosSitClas {

    @XmlElement(name = "CST", required = true)
    protected String cst;
    @XmlElement(required = true)
    protected String cClassTrib;
    protected String cCredPres;
    protected TCRTCInfoTributosTribRegular gTribRegular;
    protected TCRTCInfoTributosDif gDif;

    /**
     * Obtém o valor da propriedade cst.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCST() {
        return cst;
    }

    /**
     * Define o valor da propriedade cst.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCST(String value) {
        this.cst = value;
    }

    /**
     * Obtém o valor da propriedade cClassTrib.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCClassTrib() {
        return cClassTrib;
    }

    /**
     * Define o valor da propriedade cClassTrib.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCClassTrib(String value) {
        this.cClassTrib = value;
    }

    /**
     * Obtém o valor da propriedade cCredPres.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCCredPres() {
        return cCredPres;
    }

    /**
     * Define o valor da propriedade cCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCCredPres(String value) {
        this.cCredPres = value;
    }

    /**
     * Obtém o valor da propriedade gTribRegular.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCInfoTributosTribRegular }
     *     
     */
    public TCRTCInfoTributosTribRegular getGTribRegular() {
        return gTribRegular;
    }

    /**
     * Define o valor da propriedade gTribRegular.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCInfoTributosTribRegular }
     *     
     */
    public void setGTribRegular(TCRTCInfoTributosTribRegular value) {
        this.gTribRegular = value;
    }

    /**
     * Obtém o valor da propriedade gDif.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCInfoTributosDif }
     *     
     */
    public TCRTCInfoTributosDif getGDif() {
        return gDif;
    }

    /**
     * Define o valor da propriedade gDif.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCInfoTributosDif }
     *     
     */
    public void setGDif(TCRTCInfoTributosDif value) {
        this.gDif = value;
    }

}
