package br.com.nfse.exception;

/**
 *
 * @author eduardo
 */
public class XsdSchemaValidateException extends RuntimeException {

    public XsdSchemaValidateException() {
        super();
    }

    public XsdSchemaValidateException(String message) {
        super(message);
    }

    public XsdSchemaValidateException(String message, Throwable exception) {
        super(message, exception);
    }

    public XsdSchemaValidateException(Throwable exception) {
        super(exception);
    }

}
