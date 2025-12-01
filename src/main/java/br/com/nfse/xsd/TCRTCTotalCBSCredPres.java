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
 * <p>Classe Java de TCRTCTotalCBSCredPres complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCTotalCBSCredPres">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pCredPresCBS" type="{http://www.sped.fazenda.gov.br/nfse}TSDec2V2"/>
 *         &lt;element name="vCredPresCBS" type="{http://www.sped.fazenda.gov.br/nfse}TSDec15V2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCTotalCBSCredPres", propOrder = {
    "pCredPresCBS",
    "vCredPresCBS"
})
public class TCRTCTotalCBSCredPres {

    @XmlElement(required = true)
    protected String pCredPresCBS;
    @XmlElement(required = true)
    protected String vCredPresCBS;

    /**
     * Obtém o valor da propriedade pCredPresCBS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCredPresCBS() {
        return pCredPresCBS;
    }

    /**
     * Define o valor da propriedade pCredPresCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCredPresCBS(String value) {
        this.pCredPresCBS = value;
    }

    /**
     * Obtém o valor da propriedade vCredPresCBS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCredPresCBS() {
        return vCredPresCBS;
    }

    /**
     * Define o valor da propriedade vCredPresCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCredPresCBS(String value) {
        this.vCredPresCBS = value;
    }

}
