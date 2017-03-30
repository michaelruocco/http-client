package uk.co.mruoc.http.client.insecure;

import uk.co.mruoc.http.client.SimpleHttpClient;

public class InsecureSimpleHttpClient extends SimpleHttpClient {

    public InsecureSimpleHttpClient() {
        super(InsecureHttpClientFactory.build());
    }

}
