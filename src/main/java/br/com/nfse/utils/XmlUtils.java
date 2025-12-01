package br.com.nfse.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author eduardo
 */
public class XmlUtils {

    public static <T> T xmlToObject(String xml, Class<T> classe) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(classe);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), classe).getValue();
    }

    public static String objectToXml(Object object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty("jaxb.encoding", "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        if (object.getClass().isAnnotationPresent(XmlRootElement.class)) {
            marshaller.marshal(object, sw);
        } else {
            switch (object.getClass().getSimpleName()) {
                case "TCDPS": {
                    JAXBElement element = new JAXBElement(
                            new QName("DPS"),
                            object.getClass(),
                            object
                    );
                    marshaller.marshal(element, sw);
                    break;
                }
                case "TCPedRegEvt": {
                    JAXBElement element = new JAXBElement(
                            new QName("pedRegEvento"),
                            object.getClass(),
                            object
                    );
                    marshaller.marshal(element, sw);
                    break;
                }
                default:
                    throw new AssertionError("Não deifinido o elemento root para [" + object.getClass().getSimpleName() + "].");
            }
        }

        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(sw.toString());

        return cleanXml(xml.toString());
    }

    private static String cleanXml(String xml) {
        xml = xml.replaceAll("<!\\[CDATA\\[<!\\[CDATA\\[", "<!\\[CDATA\\[")
                .replaceAll("\\]\\]>\\]\\]>", "\\]\\]>")
                .replaceAll("ns2:", "")
                .replaceAll("ns3:", "")
                .replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">")
                .replaceAll("<Signature>", "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">")
                .replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "")
                .replaceAll(" xmlns=\"\" xmlns:ns3=\"http://www.sped.fazenda.gov.br/nfse\"", "")
                .replaceAll("<DPS>", "<DPS xmlns=\"http://www.sped.fazenda.gov.br/nfse\">")
                .replaceAll("xmlns:ns3=\"http://www.sped.fazenda.gov.br/nfse\"", "xmlns=\"http://www.sped.fazenda.gov.br/nfse\"");
        return xml;
    }

    public static String gZipToXml(byte[] data) throws IOException {
        if (data == null || data.length == 0) {
            return "";
        }

        try (GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(data))) {
            BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
            StringBuilder outStr = new StringBuilder();
            String line;
            while ((line = bf.readLine()) != null) {
                outStr.append(line);
            }

            return outStr.toString();
        }
    }

    public static byte[] xmlToGzip(String xml) throws IOException {
        if (xml == null || xml.isEmpty()) {
            return null;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (GZIPOutputStream gos = new GZIPOutputStream(baos)) {
            gos.write(xml.getBytes(StandardCharsets.UTF_8));
        }

        return baos.toByteArray();
    }

    public static String xmlToGzipB64(String xml) throws IOException {
        if (xml == null || xml.isEmpty()) {
            return "";
        }

        return Base64.getEncoder().encodeToString(xmlToGzip(xml));
    }

    public static String gZipB64ToXml(String base64) throws IOException {
        if (base64 == null || base64.isEmpty()) {
            return "";
        }

        byte[] gzipped = Base64.getDecoder().decode(base64);

        try (GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(gzipped)); InputStreamReader reader = new InputStreamReader(gis, StandardCharsets.UTF_8); BufferedReader br = new BufferedReader(reader)) {

            StringBuilder out = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                out.append(line);
            }

            return out.toString();
        }
    }
}
