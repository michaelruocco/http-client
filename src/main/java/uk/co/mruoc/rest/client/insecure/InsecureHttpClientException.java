package uk.co.mruoc.rest.client.insecure;

import uk.co.mruoc.rest.client.HttpClientException;

public class InsecureHttpClientException extends HttpClientException {

    public InsecureHttpClientException(Throwable cause) {
        super(cause);
    }

}
