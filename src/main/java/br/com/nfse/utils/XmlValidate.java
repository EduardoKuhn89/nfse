package br.com.nfse.utils;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlValidate implements ErrorHandler {

    private static final String MAX_OCCUR_LIMIT = "9999";
    private static final String SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final String SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

    private static final List<String> IGNORED_ERROR_PREFIXES = List.of(
            "cvc-enumeration-valid",
            "cvc-pattern-valid",
            "cvc-maxLength-valid",
            "cvc-datatype"
    );

    private final List<String> errosList = new ArrayList<>();
    private final String pathSchemas;
    private final String xml;
    private final String xsd;
    private boolean secureProcessing = true;
    private boolean ignoreWarnings = false;
    private boolean allowExternalSchema = true;

    private XmlValidate(Builder builder) {
        this.pathSchemas = builder.pathSchemas;
        this.xml = builder.xml;
        this.xsd = builder.xsd;
        this.secureProcessing = builder.secureProcessing;
        this.ignoreWarnings = builder.ignoreWarnings;
        this.allowExternalSchema = builder.allowExternalSchema;
    }

    public static class Builder {

        private String pathSchemas;
        private String xml;
        private String xsd;
        private boolean secureProcessing = true;
        private boolean ignoreWarnings = false;
        private boolean allowExternalSchema = true;

        public Builder pathSchemas(String pathSchemas) {
            this.pathSchemas = pathSchemas;
            return this;
        }

        public Builder xml(String xml) {
            this.xml = xml;
            return this;
        }

        public Builder xsd(String xsd) {
            this.xsd = xsd;
            return this;
        }

        public Builder secureProcessing(boolean secureProcessing) {
            this.secureProcessing = secureProcessing;
            return this;
        }

        public Builder ignoreWarnings(boolean ignoreWarnings) {
            this.ignoreWarnings = ignoreWarnings;
            return this;
        }

        public Builder allowExternalSchema(boolean allowExternalSchema) {
            this.allowExternalSchema = allowExternalSchema;
            return this;
        }

        public XmlValidate build() {
            if (pathSchemas == null) {
                throw new IllegalStateException("O diretório dos schemas é obrigatório");
            }
            if (xml == null || xml.trim().isEmpty()) {
                throw new IllegalStateException("XML é obrigatório");
            }
            if (xsd == null || xsd.trim().isEmpty()) {
                throw new IllegalStateException("XSD é obrigatório");
            }
            return new XmlValidate(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public void validate() throws Exception {
        configurarPropriedadesSistema();

        String caminhoXsd = this.pathSchemas + File.separator + xsd;
        validarExistenciaSchema(caminhoXsd);

        String erros = xmlValidateBySchema(xml, caminhoXsd);

        if (!StringUtils.isNullOrEmpty(erros)) {
            throw new Exception("Erro(s) no XML:\n" + erros);
        }
    }

    private void configurarPropriedadesSistema() {
        System.setProperty("jdk.xml.maxOccurLimit", MAX_OCCUR_LIMIT);

        if (allowExternalSchema) {
            System.setProperty("javax.xml.accessExternalSchema", "file,http,https");
            System.setProperty("javax.xml.accessExternalDTD", "file,http,https");
        }
    }

    public List<String> validateAndReturnErrors() throws Exception {
        configurarPropriedadesSistema();

        String caminhoXsd = this.pathSchemas + File.separator + xsd;
        validarExistenciaSchema(caminhoXsd);

        xmlValidateBySchema(xml, caminhoXsd);

        return new ArrayList<>(errosList);
    }

    public boolean isValid() {
        try {
            configurarPropriedadesSistema();

            String caminhoXsd = this.pathSchemas + File.separator + xsd;
            validarExistenciaSchema(caminhoXsd);

            String erros = xmlValidateBySchema(xml, caminhoXsd);
            return StringUtils.isNullOrEmpty(erros);

        } catch (Exception e) {
            return false;
        }
    }

    private void validarExistenciaSchema(String caminhoXsd) throws Exception {
        File arquivoXsd = new File(caminhoXsd);
        if (!arquivoXsd.exists()) {
            throw new Exception("Schema não localizado: " + caminhoXsd);
        }
    }

    private String xmlValidateBySchema(String xml, String caminhoXsd) throws Exception {
        errosList.clear();

        DocumentBuilder builder = createDocumentBuilder(caminhoXsd);
        parse(builder, xml);

        return String.join("\n", errosList);
    }

    private DocumentBuilder createDocumentBuilder(String caminhoXsd) throws Exception {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);

            if (secureProcessing) {
                factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
                configurarSeguranca(factory);
            }

            configureAccessSchemas(factory);

            factory.setAttribute(SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            factory.setAttribute(SCHEMA_SOURCE, caminhoXsd);

            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(this);

            return builder;
        } catch (ParserConfigurationException e) {
            throw new Exception("Erro ao configurar parser XML: " + e.getMessage(), e);
        }
    }

    private void configureAccessSchemas(DocumentBuilderFactory factory) {
        try {
            factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalSchema", "file,http,https");
            factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "file,http,https");

            factory.setAttribute("http://apache.org/xml/features/validation/schema", true);
            factory.setAttribute("http://apache.org/xml/features/validation/schema-full-checking", true);

        } catch (IllegalArgumentException e) {
            System.err.println("Aviso: Não foi possível configurar acesso a schemas externos: " + e.getMessage());
        }
    }

    private void configurarSeguranca(DocumentBuilderFactory factory) {
        try {
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", true);
            factory.setXIncludeAware(false);
        } catch (ParserConfigurationException e) {
            System.err.println("Aviso: Não foi possível configurar todas as features de segurança: " + e.getMessage());
        }
    }

    private void parse(DocumentBuilder builder, String xml) throws Exception {
        try {
            builder.parse(new InputSource(new StringReader(xml)));
        } catch (SAXException e) {
            if (errosList.isEmpty()) {
                throw new Exception("Erro ao processar XML: " + e.getMessage(), e);
            }
        } catch (IOException e) {
            throw new Exception("Erro de IO ao processar XML: " + e.getMessage(), e);
        }
    }

    @Override
    public void error(SAXParseException exception) {
        if (deveProcessarErro(exception)) {
            errosList.add("[ERRO] " + formatarMensagemErro(exception));
        }
    }

    @Override
    public void fatalError(SAXParseException exception) {
        errosList.add("[ERRO FATAL] " + formatarMensagemErro(exception));
    }

    @Override
    public void warning(SAXParseException exception) {
        if (!ignoreWarnings) {
            errosList.add("[AVISO] " + formatarMensagemErro(exception));
        }
    }

    private boolean deveProcessarErro(SAXParseException exception) {
        if (exception == null || exception.getMessage() == null) {
            return true;
        }
        String mensagem = exception.getMessage();
        return IGNORED_ERROR_PREFIXES.stream().noneMatch(mensagem::startsWith);
    }

    private String formatarMensagemErro(SAXParseException exception) {
        String mensagem = exception.getMessage();

        mensagem = removerPrefixos(mensagem);
        mensagem = traduzir(mensagem);
        mensagem = cleanCaracteres(mensagem);

        return mensagem.trim() + " (Linha: " + exception.getLineNumber() + ", Coluna: " + exception.getColumnNumber() + ")";
    }

    private String removerPrefixos(String mensagem) {
        return mensagem
                .replaceAll("cvc-type\\.3\\.1\\.3:", "-")
                .replaceAll("cvc-attribute\\.3:", "-")
                .replaceAll("cvc-complex-type\\.2\\.4\\.[a-d]:", "-")
                .replaceAll("cvc-complex-type\\.4:", "-")
                .replaceAll("cvc-minLength-valid:", "-");
    }

    private String traduzir(String mensagem) {
        return mensagem
                .replace("The value", "O valor")
                .replace("Value", "Valor")
                .replace("with length", "com tamanho")
                .replace("is not facet-valid with respect to minLength", "não equivale ao tamanho mínimo")
                .replace("for type", "para o tipo")
                .replace("The content", "O conteúdo")
                .replace("of element", "do campo")
                .replace("is not complete", "não está completo")
                .replace("is not valid", "não é válido")
                .replace("Attribute", "Campo")
                .replace("must appear on element", "precisa estar em")
                .replace("Invalid content was found starting with element", "Conteúdo inválido encontrado iniciando com o campo")
                .replace("One of", "Um dos Campos")
                .replace("is expected", "é esperado");
    }

    private String cleanCaracteres(String mensagem) {
        return mensagem
                .replaceAll("[{}\"']", "")
                .replace("http://www.sped.fazenda.gov.br/nfse:", "");
    }

    public List<String> getErros() {
        return new ArrayList<>(errosList);
    }

    public void clearErros() {
        errosList.clear();
    }
}
