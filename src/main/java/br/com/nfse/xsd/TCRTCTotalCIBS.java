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
 * <p>Classe Java de TCRTCTotalCIBS complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCTotalCIBS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vTotNF" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *         &lt;element name="gIBS" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCTotalIBS"/>
 *         &lt;element name="gCBS" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCTotalCBS"/>
 *         &lt;element name="gTribRegular" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCTotalTribRegular" minOccurs="0"/>
 *         &lt;element name="gTribCompraGov" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCTotalTribCompraGov" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCTotalCIBS", propOrder = {
    "vTotNF",
    "gibs",
    "gcbs",
    "gTribRegular",
    "gTribCompraGov"
})
public class TCRTCTotalCIBS {

    @XmlElement(required = true)
    protected String vTotNF;
    @XmlElement(name = "gIBS", required = true)
    protected TCRTCTotalIBS gibs;
    @XmlElement(name = "gCBS", required = true)
    protected TCRTCTotalCBS gcbs;
    protected TCRTCTotalTribRegular gTribRegular;
    protected TCRTCTotalTribCompraGov gTribCompraGov;

    /**
     * Obtém o valor da propriedade vTotNF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTotNF() {
        return vTotNF;
    }

    /**
     * Define o valor da propriedade vTotNF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTotNF(String value) {
        this.vTotNF = value;
    }

    /**
     * Obtém o valor da propriedade gibs.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCTotalIBS }
     *     
     */
    public TCRTCTotalIBS getGIBS() {
        return gibs;
    }

    /**
     * Define o valor da propriedade gibs.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCTotalIBS }
     *     
     */
    public void setGIBS(TCRTCTotalIBS value) {
        this.gibs = value;
    }

    /**
     * Obtém o valor da propriedade gcbs.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCTotalCBS }
     *     
     */
    public TCRTCTotalCBS getGCBS() {
        return gcbs;
    }

    /**
     * Define o valor da propriedade gcbs.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCTotalCBS }
     *     
     */
    public void setGCBS(TCRTCTotalCBS value) {
        this.gcbs = value;
    }

    /**
     * Obtém o valor da propriedade gTribRegular.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCTotalTribRegular }
     *     
     */
    public TCRTCTotalTribRegular getGTribRegular() {
        return gTribRegular;
    }

    /**
     * Define o valor da propriedade gTribRegular.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCTotalTribRegular }
     *     
     */
    public void setGTribRegular(TCRTCTotalTribRegular value) {
        this.gTribRegular = value;
    }

    /**
     * Obtém o valor da propriedade gTribCompraGov.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCTotalTribCompraGov }
     *     
     */
    public TCRTCTotalTribCompraGov getGTribCompraGov() {
        return gTribCompraGov;
    }

    /**
     * Define o valor da propriedade gTribCompraGov.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCTotalTribCompraGov }
     *     
     */
    public void setGTribCompraGov(TCRTCTotalTribCompraGov value) {
        this.gTribCompraGov = value;
    }

}
