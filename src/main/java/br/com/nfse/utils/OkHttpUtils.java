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
import java.net.Socket;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Response;

public final class OkHttpUtils {

    public static OkHttpClient createHttpClient(OkHttpClient existingClient, CertificateManager certificado, Protocol httpProtocol,
            int connectTimeoutMillis, int readTimeoutMillis, int writeTimeoutMillis) throws Exception {

        if (existingClient != null) {
            return existingClient;
        }

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .protocols(Collections.singletonList(httpProtocol))
                .connectTimeout(connectTimeoutMillis, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeoutMillis, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeoutMillis, TimeUnit.MILLISECONDS);

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

        String sslProtocol = (certificado.getProtocol() != null && !certificado.getProtocol().isEmpty())
                ? certificado.getProtocol()
                : "TLS";

        KeyStore ks = loadKeyStore(certificado);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, certificado.getPassword().toCharArray());

        KeyManager[] keyManagers = kmf.getKeyManagers();
        for (int i = 0; i < keyManagers.length; i++) {
            if (keyManagers[i] instanceof X509ExtendedKeyManager) {
                keyManagers[i] = new AliasForcingKeyManager((X509ExtendedKeyManager) keyManagers[i], ks);
            }
        }

        SSLContext sslContext = SSLContext.getInstance(sslProtocol);
        sslContext.init(keyManagers, new TrustManager[]{trustAllCerts}, new SecureRandom());

        clientBuilder.sslSocketFactory(sslContext.getSocketFactory(), trustAllCerts);
        clientBuilder.hostnameVerifier((hostname, session) -> true);
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

    public static class AliasForcingKeyManager extends X509ExtendedKeyManager {

        private final X509ExtendedKeyManager baseKeyManager;
        private final KeyStore keyStore;

        public AliasForcingKeyManager(X509ExtendedKeyManager baseKeyManager, KeyStore keyStore) {
            this.baseKeyManager = baseKeyManager;
            this.keyStore = keyStore;
        }

        @Override
        public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) {
            try {
                java.util.Enumeration<String> aliases = keyStore.aliases();
                while (aliases.hasMoreElements()) {
                    String alias = aliases.nextElement();
                    if (keyStore.isKeyEntry(alias)) {
                        //retorna o primeiro alias que possui chave privada (o e-CNPJ/e-CPF)
                        return alias;
                    }
                }
            } catch (Exception e) {
            }
            return baseKeyManager.chooseClientAlias(keyType, issuers, socket);
        }

        @Override
        public String[] getClientAliases(String keyType, Principal[] issuers) {
            return baseKeyManager.getClientAliases(keyType, issuers);
        }

        @Override
        public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
            return baseKeyManager.chooseServerAlias(keyType, issuers, socket);
        }

        @Override
        public X509Certificate[] getCertificateChain(String alias) {
            return baseKeyManager.getCertificateChain(alias);
        }

        @Override
        public String[] getServerAliases(String keyType, Principal[] issuers) {
            return baseKeyManager.getServerAliases(keyType, issuers);
        }

        @Override
        public PrivateKey getPrivateKey(String alias) {
            return baseKeyManager.getPrivateKey(alias);
        }
    }
}
