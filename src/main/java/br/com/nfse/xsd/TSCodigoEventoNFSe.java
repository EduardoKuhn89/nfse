//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2026.05.20 às 09:26:40 AM BRT 
//


package br.com.nfse.xsd;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de TSCodigoEventoNFSe.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TSCodigoEventoNFSe">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="e101101"/>
 *     &lt;enumeration value="e105102"/>
 *     &lt;enumeration value="e105104"/>
 *     &lt;enumeration value="e105105"/>
 *     &lt;enumeration value="e305101"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TSCodigoEventoNFSe")
@XmlEnum
public enum TSCodigoEventoNFSe {

    @XmlEnumValue("e101101")
    E_101101("e101101"),
    @XmlEnumValue("e105102")
    E_105102("e105102"),
    @XmlEnumValue("e105104")
    E_105104("e105104"),
    @XmlEnumValue("e105105")
    E_105105("e105105"),
    @XmlEnumValue("e305101")
    E_305101("e305101");
    private final String value;

    TSCodigoEventoNFSe(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TSCodigoEventoNFSe fromValue(String v) {
        for (TSCodigoEventoNFSe c: TSCodigoEventoNFSe.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
