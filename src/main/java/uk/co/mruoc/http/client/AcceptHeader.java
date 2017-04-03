package uk.co.mruoc.http.client;

import org.apache.http.HttpHeaders;

public class AcceptHeader extends BasicHeader {

    public AcceptHeader(String contentType) {
        super(HttpHeaders.ACCEPT, contentType);
    }

}
