package uk.co.mruoc.http.client;

import uk.co.mruoc.http.client.header.Headers;

public interface HttpClient extends ReadOnlyHttpClient {

    Response post(String endpoint, String entity);
    Response post(String endpoint, String entity, Headers headers);

    Response put(String endpoint, String entity);
    Response put(String endpoint, String entity, Headers headers);

    Response delete(String endpoint);
    Response delete(String endpoint, Headers headers);

}
