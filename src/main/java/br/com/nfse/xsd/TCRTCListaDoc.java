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
 * <p>Classe Java de TCRTCListaDoc complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCListaDoc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="dFeNacional" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCListaDocDFe"/>
 *           &lt;element name="docFiscalOutro" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCListaDocFiscalOutro"/>
 *           &lt;element name="docOutro" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCListaDocOutro"/>
 *         &lt;/choice>
 *         &lt;element name="fornec" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCListaDocFornec" minOccurs="0"/>
 *         &lt;element name="dtEmiDoc" type="{http://www.sped.fazenda.gov.br/nfse}TSData"/>
 *         &lt;element name="dtCompDoc" type="{http://www.sped.fazenda.gov.br/nfse}TSData"/>
 *         &lt;element name="tpReeRepRes" type="{http://www.sped.fazenda.gov.br/nfse}TSRTCTpReeRepRes"/>
 *         &lt;element name="xTpReeRepRes" type="{http://www.sped.fazenda.gov.br/nfse}TSDesc150" minOccurs="0"/>
 *         &lt;element name="vlrReeRepRes" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCListaDoc", propOrder = {
    "dFeNacional",
    "docFiscalOutro",
    "docOutro",
    "fornec",
    "dtEmiDoc",
    "dtCompDoc",
    "tpReeRepRes",
    "xTpReeRepRes",
    "vlrReeRepRes"
})
public class TCRTCListaDoc {

    protected TCRTCListaDocDFe dFeNacional;
    protected TCRTCListaDocFiscalOutro docFiscalOutro;
    protected TCRTCListaDocOutro docOutro;
    protected TCRTCListaDocFornec fornec;
    @XmlElement(required = true)
    protected String dtEmiDoc;
    @XmlElement(required = true)
    protected String dtCompDoc;
    @XmlElement(required = true)
    protected String tpReeRepRes;
    protected String xTpReeRepRes;
    @XmlElement(required = true)
    protected String vlrReeRepRes;

    /**
     * Obtém o valor da propriedade dFeNacional.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCListaDocDFe }
     *     
     */
    public TCRTCListaDocDFe getDFeNacional() {
        return dFeNacional;
    }

    /**
     * Define o valor da propriedade dFeNacional.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCListaDocDFe }
     *     
     */
    public void setDFeNacional(TCRTCListaDocDFe value) {
        this.dFeNacional = value;
    }

    /**
     * Obtém o valor da propriedade docFiscalOutro.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCListaDocFiscalOutro }
     *     
     */
    public TCRTCListaDocFiscalOutro getDocFiscalOutro() {
        return docFiscalOutro;
    }

    /**
     * Define o valor da propriedade docFiscalOutro.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCListaDocFiscalOutro }
     *     
     */
    public void setDocFiscalOutro(TCRTCListaDocFiscalOutro value) {
        this.docFiscalOutro = value;
    }

    /**
     * Obtém o valor da propriedade docOutro.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCListaDocOutro }
     *     
     */
    public TCRTCListaDocOutro getDocOutro() {
        return docOutro;
    }

    /**
     * Define o valor da propriedade docOutro.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCListaDocOutro }
     *     
     */
    public void setDocOutro(TCRTCListaDocOutro value) {
        this.docOutro = value;
    }

    /**
     * Obtém o valor da propriedade fornec.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCListaDocFornec }
     *     
     */
    public TCRTCListaDocFornec getFornec() {
        return fornec;
    }

    /**
     * Define o valor da propriedade fornec.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCListaDocFornec }
     *     
     */
    public void setFornec(TCRTCListaDocFornec value) {
        this.fornec = value;
    }

    /**
     * Obtém o valor da propriedade dtEmiDoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDtEmiDoc() {
        return dtEmiDoc;
    }

    /**
     * Define o valor da propriedade dtEmiDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDtEmiDoc(String value) {
        this.dtEmiDoc = value;
    }

    /**
     * Obtém o valor da propriedade dtCompDoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDtCompDoc() {
        return dtCompDoc;
    }

    /**
     * Define o valor da propriedade dtCompDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDtCompDoc(String value) {
        this.dtCompDoc = value;
    }

    /**
     * Obtém o valor da propriedade tpReeRepRes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpReeRepRes() {
        return tpReeRepRes;
    }

    /**
     * Define o valor da propriedade tpReeRepRes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpReeRepRes(String value) {
        this.tpReeRepRes = value;
    }

    /**
     * Obtém o valor da propriedade xTpReeRepRes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXTpReeRepRes() {
        return xTpReeRepRes;
    }

    /**
     * Define o valor da propriedade xTpReeRepRes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXTpReeRepRes(String value) {
        this.xTpReeRepRes = value;
    }

    /**
     * Obtém o valor da propriedade vlrReeRepRes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVlrReeRepRes() {
        return vlrReeRepRes;
    }

    /**
     * Define o valor da propriedade vlrReeRepRes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVlrReeRepRes(String value) {
        this.vlrReeRepRes = value;
    }

}
