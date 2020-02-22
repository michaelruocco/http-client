package uk.co.mruoc.rest.client.insecure;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class InsecureHttpClientFactory {

    public static HttpClient build() {
        SSLContext sslContext = buildSslContext();
        return HttpClientBuilder.create()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
    }

    private static SSLContext buildSslContext() {
        try {
            return SSLContexts.custom()
                    .loadTrustMaterial(new InsecureTrustStrategy())
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            throw new InsecureHttpClientException(e);
        }
    }

}
