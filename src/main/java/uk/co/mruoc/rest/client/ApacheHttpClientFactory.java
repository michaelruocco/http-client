package uk.co.mruoc.rest.client;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultRoutePlanner;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;

import java.net.ProxySelector;

public class ApacheHttpClientFactory {

    public static CloseableHttpClient build() {
        return createBuilder().build();
    }

    public static CloseableHttpClient build(int httpTimeout) {
        return createBuilder()
                .setDefaultRequestConfig(defaultRequestConfig(httpTimeout))
                .build();
    }

    private static RequestConfig defaultRequestConfig(int httpTimeout) {
        return RequestConfig.custom().setSocketTimeout(httpTimeout).build();
    }

    private static DefaultRoutePlanner createDefaultProxyRoutePlanner() {
        return new SystemDefaultRoutePlanner(ProxySelector.getDefault());
    }

    private static HttpClientBuilder createBuilder() {
        return HttpClientBuilder
                .create()
                .setRoutePlanner(createDefaultProxyRoutePlanner());
    }

}

