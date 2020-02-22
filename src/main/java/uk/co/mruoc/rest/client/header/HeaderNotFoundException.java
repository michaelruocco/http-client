package uk.co.mruoc.rest.client.header;

import uk.co.mruoc.rest.client.HttpClientException;

public class HeaderNotFoundException extends HttpClientException {

    public HeaderNotFoundException(String headerName) {
        super(headerName);
    }

}
