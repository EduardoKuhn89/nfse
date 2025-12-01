package br.com.nfse.utils;

import br.com.nfse.CertificateManager;
import br.com.nfse.ConfigManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author eduardo
 */
public class XmlSigner {

    private XmlSigner() {

    }

    public static XmlSignerBuilder builder() {
        return new XmlSignerBuilder();
    }

    public static class XmlSignerBuilder {

        private ConfigManager config;

        private String xml;
        private String elementSignPosition;
        private String elementContent;
        private String attrId = "Id";

        private boolean withComments = false;
        private boolean idented = false;
        private boolean showDeclaration = true;
        private DigestAlgorithm digestAlgorithm = DigestAlgorithm.SHA1;
        private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RSA_SHA1;

        private XmlSignerBuilder() {

        }

        public XmlSignerBuilder xml(String xml) {
            this.xml = xml;
            return this;
        }

        public XmlSignerBuilder signPosition(String elementSignPosition) {
            this.elementSignPosition = elementSignPosition;
            return this;
        }

        public XmlSignerBuilder elementToSign(String elementContent) {
            this.elementContent = elementContent;
            return this;
        }

        public XmlSignerBuilder attributeId(String attrId) {
            this.attrId = attrId;
            return this;
        }

        public XmlSignerBuilder config(ConfigManager config) {
            this.config = config;
            return this;
        }

        public XmlSignerBuilder withComments(boolean withComments) {
            this.withComments = withComments;
            return this;
        }

        public XmlSignerBuilder idented(boolean idented) {
            this.idented = idented;
            return this;
        }

        public XmlSignerBuilder showDeclaration(boolean showDeclaration) {
            this.showDeclaration = showDeclaration;
            return this;
        }

        public XmlSignerBuilder digestAlgorithm(DigestAlgorithm algorithm) {
            this.digestAlgorithm = algorithm;
            return this;
        }

        public XmlSignerBuilder signatureAlgorithm(SignatureAlgorithm algorithm) {
            this.signatureAlgorithm = algorithm;
            return this;
        }

        public XmlSignerBuilder sha1() {
            this.digestAlgorithm = DigestAlgorithm.SHA1;
            this.signatureAlgorithm = SignatureAlgorithm.RSA_SHA1;
            return this;
        }

        public XmlSignerBuilder sha256() {
            this.digestAlgorithm = DigestAlgorithm.SHA256;
            this.signatureAlgorithm = SignatureAlgorithm.RSA_SHA256;
            return this;
        }

        public String sign() throws Exception {
            validateParameters();

            String cleanedXml = cleanXml(xml);
            Document doc = loadXml(cleanedXml);

            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            // Configura elemento a ser assinado
            Element elementToSign = getElementToSign(doc);
            String elementId = validateAndGetElementId(elementToSign);

            // Cria referência da assinatura
            Reference ref = createReference(fac, elementId);

            // Cria informações da assinatura
            SignedInfo signedInfo = createSignedInfo(fac, ref);

            // Cria contexto de assinatura
            DOMSignContext signContext = createSignContext(doc);

            // Cria informações da chave
            KeyInfo keyInfo = createKeyInfo(fac);

            // Executa assinatura
            XMLSignature signature = fac.newXMLSignature(signedInfo, keyInfo);
            signature.sign(signContext);

            return documentToString(doc, idented, showDeclaration);
        }

        private void validateParameters() {
            if (xml == null || xml.trim().isEmpty()) {
                throw new IllegalArgumentException("XML não pode ser nulo ou vazio");
            }
            if (elementSignPosition == null || elementSignPosition.trim().isEmpty()) {
                throw new IllegalArgumentException("Elemento de posição de assinatura não pode ser nulo ou vazio");
            }
            if (elementContent == null || elementContent.trim().isEmpty()) {
                throw new IllegalArgumentException("Elemento a ser assinado não pode ser nulo ou vazio");
            }
            if (config == null) {
                throw new IllegalArgumentException("Configuração não pode ser nula");
            }
        }

        private Element getElementToSign(Document doc) throws Exception {
            NodeList nl = doc.getElementsByTagNameNS("*", elementContent);
            if (nl.getLength() != 1) {
                throw new Exception("Elemento " + elementContent + " não encontrado ou múltiplo.");
            }
            return (Element) nl.item(0);
        }

        private String validateAndGetElementId(Element element) throws Exception {
            if (!element.hasAttribute(attrId)) {
                throw new Exception("Atributo '" + attrId + "' não encontrado no elemento " + elementContent);
            }
            element.setIdAttribute(attrId, true);
            return element.getAttribute(attrId);
        }

        private Reference createReference(XMLSignatureFactory fac, String elementId) throws Exception {
            Transform enveloped = fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null);
            Transform c14nTransform = fac.newTransform(
                    withComments ? CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS : CanonicalizationMethod.INCLUSIVE,
                    (TransformParameterSpec) null);

            return fac.newReference(
                    "#" + elementId,
                    fac.newDigestMethod(digestAlgorithm.getUri(), null),
                    Arrays.asList(enveloped, c14nTransform),
                    null,
                    null
            );
        }

        private SignedInfo createSignedInfo(XMLSignatureFactory fac, Reference ref) throws Exception {
            CanonicalizationMethod c14nMethod = fac.newCanonicalizationMethod(
                    withComments ? CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS : CanonicalizationMethod.INCLUSIVE,
                    (C14NMethodParameterSpec) null
            );

            SignatureMethod signatureMethod = fac.newSignatureMethod(
                    signatureAlgorithm.getUri(),
                    null
            );

            return fac.newSignedInfo(c14nMethod, signatureMethod, Collections.singletonList(ref));
        }

        private DOMSignContext createSignContext(Document doc) throws Exception {
            NodeList positionNodes = doc.getElementsByTagName(elementSignPosition);
            if (positionNodes.getLength() == 0) {
                throw new Exception("Elemento de posição " + elementSignPosition + " não encontrado");
            }

            PrivateKey privateKey = getPrivateKey();
            return new DOMSignContext(privateKey, positionNodes.item(0));
        }

        private KeyInfo createKeyInfo(XMLSignatureFactory fac) throws Exception {
            X509Certificate cert = getCertificate();
            KeyInfoFactory kif = fac.getKeyInfoFactory();
            X509Data x509Data = kif.newX509Data(Collections.singletonList(cert));
            return kif.newKeyInfo(Collections.singletonList(x509Data));
        }

        private PrivateKey getPrivateKey() throws Exception {
            KeyStore keyStore = config.getCertificado().getKeyStore();
            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(
                    config.getCertificado().getAlias(),
                    new KeyStore.PasswordProtection(config.getCertificado().getPassword().toCharArray())
            );
            return pkEntry.getPrivateKey();
        }

        private X509Certificate getCertificate() throws Exception {
            KeyStore keyStore = config.getCertificado().getKeyStore();
            return CertificateManager.getX509(keyStore, config.getCertificado().getAlias());
        }
    }

    public enum DigestAlgorithm {
        SHA1("http://www.w3.org/2000/09/xmldsig#sha1"),
        SHA256("http://www.w3.org/2001/04/xmldsig-more#sha256");

        private final String uri;

        DigestAlgorithm(String uri) {
            this.uri = uri;
        }

        public String getUri() {
            return uri;
        }
    }

    public enum SignatureAlgorithm {
        RSA_SHA1("http://www.w3.org/2000/09/xmldsig#rsa-sha1"),
        RSA_SHA256("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256");

        private final String uri;

        SignatureAlgorithm(String uri) {
            this.uri = uri;
        }

        public String getUri() {
            return uri;
        }
    }

    private static Document loadXml(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new org.xml.sax.InputSource(new StringReader(xml)));
    }

    private static String documentToString(Document doc, boolean ident, boolean showDecl) throws Exception {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();

        t.setOutputProperty(OutputKeys.INDENT, ident ? "yes" : "no");
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, showDecl ? "no" : "yes");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        StringWriter sw = new StringWriter();
        t.transform(new DOMSource(doc), new StreamResult(sw));
        return sw.toString().replaceAll("\r", "").replaceAll("\n", "").replaceAll("&#13;", "");
    }

    private static String cleanXml(String xml) {
        return xml.replaceAll("\\s+<", "<")
                .replaceAll("\r", "")
                .replaceAll("\n", "")
                .replaceAll(System.lineSeparator(), "");
    }

    public static boolean validate(String xml) throws Exception {
        Document doc = loadXml(xml);

        NodeList nl = doc.getElementsByTagName("Signature");
        if (nl.getLength() != 1) {
            throw new Exception("Elemento Signature não encontrado ou múltiplo.");
        }

        NodeList certNode = doc.getElementsByTagName("X509Certificate");
        if (certNode.getLength() != 1) {
            throw new Exception("Elemento X509Certificate não encontrado ou múltiplo.");
        }

        byte[] certBytes = java.util.Base64.getDecoder().decode(certNode.item(0).getTextContent());
        X509Certificate cert = (X509Certificate) java.security.cert.CertificateFactory
                .getInstance("X.509")
                .generateCertificate(new java.io.ByteArrayInputStream(certBytes));

        DOMValidateContext valContext = new DOMValidateContext(cert.getPublicKey(), nl.item(0));
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        XMLSignature signature = fac.unmarshalXMLSignature(valContext);

        return signature.validate(valContext);
    }
}
