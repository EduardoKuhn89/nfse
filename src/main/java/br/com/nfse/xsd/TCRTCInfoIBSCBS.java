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
 * <p>Classe Java de TCRTCInfoIBSCBS complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCRTCInfoIBSCBS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="finNFSe" type="{http://www.sped.fazenda.gov.br/nfse}TSRTCFinNFSe"/>
 *         &lt;element name="indFinal" type="{http://www.sped.fazenda.gov.br/nfse}TSRTCIndFinal"/>
 *         &lt;element name="cIndOp" type="{http://www.sped.fazenda.gov.br/nfse}TSRTCCodIndOp"/>
 *         &lt;element name="tpOper" type="{http://www.sped.fazenda.gov.br/nfse}TSRTCTpOper" minOccurs="0"/>
 *         &lt;element name="gRefNFSe" type="{http://www.sped.fazenda.gov.br/nfse}TCInfoRefNFSe" minOccurs="0"/>
 *         &lt;element name="tpEnteGov" type="{http://www.sped.fazenda.gov.br/nfse}TSRTCTpEnteGov" minOccurs="0"/>
 *         &lt;element name="indDest" type="{http://www.sped.fazenda.gov.br/nfse}TSRTCIndDest"/>
 *         &lt;element name="dest" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCInfoDest" minOccurs="0"/>
 *         &lt;element name="imovel" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCInfoImovel" minOccurs="0"/>
 *         &lt;element name="valores" type="{http://www.sped.fazenda.gov.br/nfse}TCRTCInfoValoresIBSCBS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCRTCInfoIBSCBS", propOrder = {
    "finNFSe",
    "indFinal",
    "cIndOp",
    "tpOper",
    "gRefNFSe",
    "tpEnteGov",
    "indDest",
    "dest",
    "imovel",
    "valores"
})
public class TCRTCInfoIBSCBS {

    @XmlElement(required = true)
    protected String finNFSe;
    @XmlElement(required = true)
    protected String indFinal;
    @XmlElement(required = true)
    protected String cIndOp;
    protected String tpOper;
    protected TCInfoRefNFSe gRefNFSe;
    protected String tpEnteGov;
    @XmlElement(required = true)
    protected String indDest;
    protected TCRTCInfoDest dest;
    protected TCRTCInfoImovel imovel;
    @XmlElement(required = true)
    protected TCRTCInfoValoresIBSCBS valores;

    /**
     * Obtém o valor da propriedade finNFSe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinNFSe() {
        return finNFSe;
    }

    /**
     * Define o valor da propriedade finNFSe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinNFSe(String value) {
        this.finNFSe = value;
    }

    /**
     * Obtém o valor da propriedade indFinal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndFinal() {
        return indFinal;
    }

    /**
     * Define o valor da propriedade indFinal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndFinal(String value) {
        this.indFinal = value;
    }

    /**
     * Obtém o valor da propriedade cIndOp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCIndOp() {
        return cIndOp;
    }

    /**
     * Define o valor da propriedade cIndOp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCIndOp(String value) {
        this.cIndOp = value;
    }

    /**
     * Obtém o valor da propriedade tpOper.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpOper() {
        return tpOper;
    }

    /**
     * Define o valor da propriedade tpOper.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpOper(String value) {
        this.tpOper = value;
    }

    /**
     * Obtém o valor da propriedade gRefNFSe.
     * 
     * @return
     *     possible object is
     *     {@link TCInfoRefNFSe }
     *     
     */
    public TCInfoRefNFSe getGRefNFSe() {
        return gRefNFSe;
    }

    /**
     * Define o valor da propriedade gRefNFSe.
     * 
     * @param value
     *     allowed object is
     *     {@link TCInfoRefNFSe }
     *     
     */
    public void setGRefNFSe(TCInfoRefNFSe value) {
        this.gRefNFSe = value;
    }

    /**
     * Obtém o valor da propriedade tpEnteGov.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpEnteGov() {
        return tpEnteGov;
    }

    /**
     * Define o valor da propriedade tpEnteGov.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpEnteGov(String value) {
        this.tpEnteGov = value;
    }

    /**
     * Obtém o valor da propriedade indDest.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndDest() {
        return indDest;
    }

    /**
     * Define o valor da propriedade indDest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndDest(String value) {
        this.indDest = value;
    }

    /**
     * Obtém o valor da propriedade dest.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCInfoDest }
     *     
     */
    public TCRTCInfoDest getDest() {
        return dest;
    }

    /**
     * Define o valor da propriedade dest.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCInfoDest }
     *     
     */
    public void setDest(TCRTCInfoDest value) {
        this.dest = value;
    }

    /**
     * Obtém o valor da propriedade imovel.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCInfoImovel }
     *     
     */
    public TCRTCInfoImovel getImovel() {
        return imovel;
    }

    /**
     * Define o valor da propriedade imovel.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCInfoImovel }
     *     
     */
    public void setImovel(TCRTCInfoImovel value) {
        this.imovel = value;
    }

    /**
     * Obtém o valor da propriedade valores.
     * 
     * @return
     *     possible object is
     *     {@link TCRTCInfoValoresIBSCBS }
     *     
     */
    public TCRTCInfoValoresIBSCBS getValores() {
        return valores;
    }

    /**
     * Define o valor da propriedade valores.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRTCInfoValoresIBSCBS }
     *     
     */
    public void setValores(TCRTCInfoValoresIBSCBS value) {
        this.valores = value;
    }

}
