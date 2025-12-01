//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2025.11.28 às 01:51:46 PM BRT 
//


package br.com.nfse.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de TCInfoCompl complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCInfoCompl">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDocTec" type="{http://www.sped.fazenda.gov.br/nfse}TSDRT" minOccurs="0"/>
 *         &lt;element name="docRef" type="{http://www.sped.fazenda.gov.br/nfse}TSDesc255" minOccurs="0"/>
 *         &lt;element name="xPed" type="{http://www.sped.fazenda.gov.br/nfse}TSNumeroEndereco" minOccurs="0"/>
 *         &lt;element name="gItemPed" type="{http://www.sped.fazenda.gov.br/nfse}TCInfoItemPed" minOccurs="0"/>
 *         &lt;element name="xInfComp" type="{http://www.sped.fazenda.gov.br/nfse}TSDescInfCompl" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCInfoCompl", propOrder = {
    "idDocTec",
    "docRef",
    "xPed",
    "gItemPed",
    "xInfComp"
})
public class TCInfoCompl {

    protected String idDocTec;
    protected String docRef;
    protected String xPed;
    protected TCInfoItemPed gItemPed;
    protected String xInfComp;

    /**
     * Obtém o valor da propriedade idDocTec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDocTec() {
        return idDocTec;
    }

    /**
     * Define o valor da propriedade idDocTec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDocTec(String value) {
        this.idDocTec = value;
    }

    /**
     * Obtém o valor da propriedade docRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocRef() {
        return docRef;
    }

    /**
     * Define o valor da propriedade docRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocRef(String value) {
        this.docRef = value;
    }

    /**
     * Obtém o valor da propriedade xPed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXPed() {
        return xPed;
    }

    /**
     * Define o valor da propriedade xPed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXPed(String value) {
        this.xPed = value;
    }

    /**
     * Obtém o valor da propriedade gItemPed.
     * 
     * @return
     *     possible object is
     *     {@link TCInfoItemPed }
     *     
     */
    public TCInfoItemPed getGItemPed() {
        return gItemPed;
    }

    /**
     * Define o valor da propriedade gItemPed.
     * 
     * @param value
     *     allowed object is
     *     {@link TCInfoItemPed }
     *     
     */
    public void setGItemPed(TCInfoItemPed value) {
        this.gItemPed = value;
    }

    /**
     * Obtém o valor da propriedade xInfComp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXInfComp() {
        return xInfComp;
    }

    /**
     * Define o valor da propriedade xInfComp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXInfComp(String value) {
        this.xInfComp = value;
    }

}
