package uk.co.mruoc.rest.client;

import uk.co.mruoc.rest.client.header.Headers;
import uk.co.mruoc.rest.client.response.Response;

public interface RestClient extends ReadOnlyRestClient {

    Response post(String endpoint, String entity);
    Response post(String endpoint, String entity, Headers headers);

    Response put(String endpoint, String entity);
    Response put(String endpoint, String entity, Headers headers);

    Response delete(String endpoint);
    Response delete(String endpoint, Headers headers);

}
