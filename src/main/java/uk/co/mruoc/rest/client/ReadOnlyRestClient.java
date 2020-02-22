package uk.co.mruoc.rest.client;

import uk.co.mruoc.rest.client.header.Headers;
import uk.co.mruoc.rest.client.response.Response;

public interface ReadOnlyRestClient {

    Response get(String endpoint);
    Response get(String endpoint, Headers headers);

}
