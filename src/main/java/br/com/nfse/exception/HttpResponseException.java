package br.com.nfse.exception;

import java.io.IOException;

/**
 *
 * @author eduardo
 */
public class HttpResponseException extends IOException {

    private final int httpCode;
    private final String httpMessage;
    private final String url;
    private final String rawBody;

    public HttpResponseException(int httpCode, String httpMessage, String url, String rawBody, Throwable cause) {
        super("HTTP " + httpCode + " - " + httpMessage + ": " + cause.getMessage(), cause);
        this.httpCode = httpCode;
        this.httpMessage = httpMessage;
        this.url = url;
        this.rawBody = rawBody;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getHttpMessage() {
        return httpMessage;
    }

    public String getUrl() {
        return url;
    }

    public String getRawBody() {
        return rawBody;
    }
}
