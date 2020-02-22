package uk.co.mruoc.http.client.header;

import uk.co.mruoc.http.client.HttpClientException;

public class HeaderNotFoundException extends HttpClientException {

    public HeaderNotFoundException(String headerName) {
        super(headerName);
    }

}
