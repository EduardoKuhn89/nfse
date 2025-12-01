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
 * <p>Classe Java de TCRTCIBSCBS complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCIBSCBS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cLocalidadeIncid" type="{http://www.sped.fazenda.gov.br/nfse}TSCodMunIBGE"/>
 *         &lt;element name="xLocalidadeIncid" type="{http://www.sped.fazenda.gov.br/nfse}TSDesc600"/>
 *         &lt;element name="pRedutor" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *         &lt;element name="valores" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCValoresIBSCBS"/>
 *         &lt;element name="totCIBS" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCTotalCIBS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCIBSCBS", propOrder = {
    "cLocalidadeIncid",
    "xLocalidadeIncid",
    "pRedutor",
    "valores",
    "totCIBS"
})
public class TCRTCIBSCBS {

    @XmlElement(required = true)
    protected String cLocalidadeIncid;
    @XmlElement(required = true)
    protected String xLocalidadeIncid;
    @XmlElement(required = true)
    protected String pRedutor;
    @XmlElement(required = true)
    protected TCRTCValoresIBSCBS valores;
    @XmlElement(required = true)
    protected TCRTCTotalCIBS totCIBS;

    /**
     * Obtém o valor da propriedade cLocalidadeIncid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLocalidadeIncid() {
        return cLocalidadeIncid;
    }

    /**
     * Define o valor da propriedade cLocalidadeIncid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLocalidadeIncid(String value) {
        this.cLocalidadeIncid = value;
    }

    /**
     * Obtém o valor da propriedade xLocalidadeIncid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXLocalidadeIncid() {
        return xLocalidadeIncid;
    }

    /**
     * Define o valor da propriedade xLocalidadeIncid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXLocalidadeIncid(String value) {
        this.xLocalidadeIncid = value;
    }

    /**
     * Obtém o valor da propriedade pRedutor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRedutor() {
        return pRedutor;
    }

    /**
     * Define o valor da propriedade pRedutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRedutor(String value) {
        this.pRedutor = value;
    }

    /**
     * Obtém o valor da propriedade valores.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCValoresIBSCBS }
     *     
     */
    public TCRTCValoresIBSCBS getValores() {
        return valores;
    }

    /**
     * Define o valor da propriedade valores.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCValoresIBSCBS }
     *     
     */
    public void setValores(TCRTCValoresIBSCBS value) {
        this.valores = value;
    }

    /**
     * Obtém o valor da propriedade totCIBS.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCTotalCIBS }
     *     
     */
    public TCRTCTotalCIBS getTotCIBS() {
        return totCIBS;
    }

    /**
     * Define o valor da propriedade totCIBS.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCTotalCIBS }
     *     
     */
    public void setTotCIBS(TCRTCTotalCIBS value) {
        this.totCIBS = value;
    }

}
