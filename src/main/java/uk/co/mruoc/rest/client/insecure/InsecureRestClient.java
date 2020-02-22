package uk.co.mruoc.rest.client.insecure;

import uk.co.mruoc.rest.client.SimpleRestClient;

public class InsecureRestClient extends SimpleRestClient {

    public InsecureRestClient() {
        super(InsecureHttpClientFactory.build());
    }

}
