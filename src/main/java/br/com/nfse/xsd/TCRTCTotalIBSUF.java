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
 * <p>Classe Java de TCRTCTotalIBSUF complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCTotalIBSUF">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vDifUF" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *         &lt;element name="vIBSUF" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCTotalIBSUF", propOrder = {
    "vDifUF",
    "vibsuf"
})
public class TCRTCTotalIBSUF {

    @XmlElement(required = true)
    protected String vDifUF;
    @XmlElement(name = "vIBSUF", required = true)
    protected String vibsuf;

    /**
     * Obtém o valor da propriedade vDifUF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVDifUF() {
        return vDifUF;
    }

    /**
     * Define o valor da propriedade vDifUF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVDifUF(String value) {
        this.vDifUF = value;
    }

    /**
     * Obtém o valor da propriedade vibsuf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIBSUF() {
        return vibsuf;
    }

    /**
     * Define o valor da propriedade vibsuf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVIBSUF(String value) {
        this.vibsuf = value;
    }

}
