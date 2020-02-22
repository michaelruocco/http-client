package uk.co.mruoc.http.client;

import uk.co.mruoc.http.client.header.Headers;

public interface ReadOnlyHttpClient {

    Response get(String endpoint);
    Response get(String endpoint, Headers headers);

}
