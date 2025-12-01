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
 * <p>Classe Java de TCRTCTotalCBS complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCTotalCBS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gCBSCredPres" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCTotalCBSCredPres" minOccurs="0"/>
 *         &lt;element name="vDifCBS" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *         &lt;element name="vCBS" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCTotalCBS", propOrder = {
    "gcbsCredPres",
    "vDifCBS",
    "vcbs"
})
public class TCRTCTotalCBS {

    @XmlElement(name = "gCBSCredPres")
    protected TCRTCTotalCBSCredPres gcbsCredPres;
    @XmlElement(required = true)
    protected String vDifCBS;
    @XmlElement(name = "vCBS", required = true)
    protected String vcbs;

    /**
     * Obtém o valor da propriedade gcbsCredPres.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCTotalCBSCredPres }
     *     
     */
    public TCRTCTotalCBSCredPres getGCBSCredPres() {
        return gcbsCredPres;
    }

    /**
     * Define o valor da propriedade gcbsCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCTotalCBSCredPres }
     *     
     */
    public void setGCBSCredPres(TCRTCTotalCBSCredPres value) {
        this.gcbsCredPres = value;
    }

    /**
     * Obtém o valor da propriedade vDifCBS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVDifCBS() {
        return vDifCBS;
    }

    /**
     * Define o valor da propriedade vDifCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVDifCBS(String value) {
        this.vDifCBS = value;
    }

    /**
     * Obtém o valor da propriedade vcbs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCBS() {
        return vcbs;
    }

    /**
     * Define o valor da propriedade vcbs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCBS(String value) {
        this.vcbs = value;
    }

}
