//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2026.05.20 às 09:26:40 AM BRT 
//


package br.com.nfse.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de TE205208 complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TE205208">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xDesc">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;whiteSpace value="preserve"/>
 *               &lt;enumeration value="Manifestação de NFS-e - Anulação da Rejeição"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CPFAgTrib" type="{http://www.sped.fazenda.gov.br/nfse}TSCPF"/>
 *         &lt;element name="idEvManifRej" type="{http://www.sped.fazenda.gov.br/nfse}TSIdNumEvento"/>
 *         &lt;element name="xMotivo" type="{http://www.sped.fazenda.gov.br/nfse}TSMotivo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TE205208", propOrder = {
    "xDesc",
    "cpfAgTrib",
    "idEvManifRej",
    "xMotivo"
})
public class TE205208 {

    @XmlElement(required = true)
    protected String xDesc;
    @XmlElement(name = "CPFAgTrib", required = true)
    protected String cpfAgTrib;
    @XmlElement(required = true)
    protected String idEvManifRej;
    @XmlElement(required = true)
    protected String xMotivo;

    /**
     * Obtém o valor da propriedade xDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXDesc() {
        return xDesc;
    }

    /**
     * Define o valor da propriedade xDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXDesc(String value) {
        this.xDesc = value;
    }

    /**
     * Obtém o valor da propriedade cpfAgTrib.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPFAgTrib() {
        return cpfAgTrib;
    }

    /**
     * Define o valor da propriedade cpfAgTrib.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPFAgTrib(String value) {
        this.cpfAgTrib = value;
    }

    /**
     * Obtém o valor da propriedade idEvManifRej.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEvManifRej() {
        return idEvManifRej;
    }

    /**
     * Define o valor da propriedade idEvManifRej.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEvManifRej(String value) {
        this.idEvManifRej = value;
    }

    /**
     * Obtém o valor da propriedade xMotivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMotivo() {
        return xMotivo;
    }

    /**
     * Define o valor da propriedade xMotivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMotivo(String value) {
        this.xMotivo = value;
    }

}
