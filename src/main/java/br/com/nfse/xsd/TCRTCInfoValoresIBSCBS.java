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
 * <p>Classe Java de TCRTCInfoValoresIBSCBS complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCInfoValoresIBSCBS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gReeRepRes" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCInfoReeRepRes" minOccurs="0"/>
 *         &lt;element name="trib" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCInfoTributosIBSCBS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCInfoValoresIBSCBS", propOrder = {
    "gReeRepRes",
    "trib"
})
public class TCRTCInfoValoresIBSCBS {

    protected TCRTCInfoReeRepRes gReeRepRes;
    @XmlElement(required = true)
    protected TCRTCInfoTributosIBSCBS trib;

    /**
     * Obtém o valor da propriedade gReeRepRes.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCInfoReeRepRes }
     *     
     */
    public TCRTCInfoReeRepRes getGReeRepRes() {
        return gReeRepRes;
    }

    /**
     * Define o valor da propriedade gReeRepRes.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCInfoReeRepRes }
     *     
     */
    public void setGReeRepRes(TCRTCInfoReeRepRes value) {
        this.gReeRepRes = value;
    }

    /**
     * Obtém o valor da propriedade trib.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCInfoTributosIBSCBS }
     *     
     */
    public TCRTCInfoTributosIBSCBS getTrib() {
        return trib;
    }

    /**
     * Define o valor da propriedade trib.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCInfoTributosIBSCBS }
     *     
     */
    public void setTrib(TCRTCInfoTributosIBSCBS value) {
        this.trib = value;
    }

}
