package br.com.nfse.utils;

import br.com.nfse.CertificateManager;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;

import javax.net.ssl.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Response;

public final class OkHttpUtils {

    public static OkHttpClient createHttpClient(OkHttpClient existingClient, CertificateManager certificado, Protocol httpProtocol, int timeoutSeconds) throws Exception {

        if (existingClient != null) {
            return existingClient;
        }

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .protocols(Collections.singletonList(httpProtocol))
                .connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .readTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .writeTimeout(timeoutSeconds, TimeUnit.SECONDS);

        applySslFactory(clientBuilder, certificado);

        clientBuilder.cookieJar(new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl hu, List<Cookie> list) {
                cookieStore.put(hu.host(), list);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl hu) {
                List<Cookie> cookies = cookieStore.get(hu.host());
                return cookies != null ? cookies : new ArrayList<>();
            }
        });

        return clientBuilder.build();
    }

    private static void applySslFactory(OkHttpClient.Builder clientBuilder, CertificateManager certificado) throws Exception {
        X509TrustManager trustAllCerts = buildTrustAllManager();
        SSLContext sslContext = buildSslContext(certificado, trustAllCerts);

        clientBuilder.sslSocketFactory(sslContext.getSocketFactory(), trustAllCerts);
        clientBuilder.hostnameVerifier((hostname, session) -> true);
    }

    private static SSLContext buildSslContext(CertificateManager certificado, X509TrustManager trustManager) throws Exception {
        if (certificado.getCertificateBytes() == null || certificado.getCertificateBytes().length == 0) {
            throw new IllegalStateException("Bytes do certificado não estão disponíveis");
        }

        KeyStore ks = loadKeyStore(certificado);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, certificado.getPassword().toCharArray());

        String sslProtocol = certificado.getProtocol() != null ? certificado.getProtocol() : "TLS";
        SSLContext sslContext = SSLContext.getInstance(sslProtocol);
        sslContext.init(kmf.getKeyManagers(), new TrustManager[]{trustManager}, new SecureRandom());

        return sslContext;
    }

    private static KeyStore loadKeyStore(CertificateManager certificado) throws Exception {
        KeyStore ks = certificado.getProvider() != null
                ? KeyStore.getInstance("PKCS12", certificado.getProvider())
                : KeyStore.getInstance("PKCS12");

        try (InputStream certStream = new ByteArrayInputStream(certificado.getCertificateBytes())) {
            ks.load(certStream, certificado.getPassword().toCharArray());
        }

        return ks;
    }

    private static X509TrustManager buildTrustAllManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        };
    }

    public static X509TrustManager getTrustManagerDefault() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + java.util.Arrays.toString(trustManagers));
            }
            return (X509TrustManager) trustManagers[0];
        } catch (IllegalStateException | KeyStoreException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao obter TrustManager padrão", e);
        }
    }

    public static String buildErrorMessage(Response response) {
        return String.format("Erro HTTP %d: %s - URL: %s",
                response.code(),
                response.message(),
                response.request().url());
    }

    public static HttpUrl.Builder toHttpUrlBuilder(String url) {
        return HttpUrl.parse(url).newBuilder();
    }
}
