package uk.co.mruoc.http.client;

public interface ReadOnlyHttpClient {

    Response get(String endpoint);
    Response get(String endpoint, Headers headers);

}
