package uk.co.mruoc.http.client;

public class HttpClientException extends RuntimeException {

    public HttpClientException(String message) {
        super(message);
    }

    public HttpClientException(Throwable cause) {
        super(cause);
    }

}
