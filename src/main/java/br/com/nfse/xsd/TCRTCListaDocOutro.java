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
 * <p>Classe Java de TCRTCListaDocOutro complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCListaDocOutro">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nDoc" type="{http://www.sped.fazenda.gov.br/nfse}TSDesc255"/>
 *         &lt;element name="xDoc" type="{http://www.sped.fazenda.gov.br/nfse}TSDesc255"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCListaDocOutro", propOrder = {
    "nDoc",
    "xDoc"
})
public class TCRTCListaDocOutro {

    @XmlElement(required = true)
    protected String nDoc;
    @XmlElement(required = true)
    protected String xDoc;

    /**
     * Obtém o valor da propriedade nDoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNDoc() {
        return nDoc;
    }

    /**
     * Define o valor da propriedade nDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNDoc(String value) {
        this.nDoc = value;
    }

    /**
     * Obtém o valor da propriedade xDoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXDoc() {
        return xDoc;
    }

    /**
     * Define o valor da propriedade xDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXDoc(String value) {
        this.xDoc = value;
    }

}
