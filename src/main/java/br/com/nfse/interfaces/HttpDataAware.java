package br.com.nfse.interfaces;

/**
 *
 * @author eduardo
 */
public interface HttpDataAware {

    void setHttpResult(int status, String message, String url, String rawBody);
}
