package br.com.nfse.dto;

/**
 *
 * @author eduardo
 */
public class HttpResult {

    private Integer code;
    private String message;
    private String url;
    private String body;

    public HttpResult() {
    }

    public HttpResult(Integer code, String message, String url, String body) {
        this.code = code;
        this.message = message;
        this.url = url;
        this.body = body;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpResult{" + "code=" + code + ", message=" + message + '}';
    }

}
