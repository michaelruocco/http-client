package uk.co.mruoc.http.client;

import org.apache.http.HttpHeaders;

public class BearerTokenHeader extends BasicHeader {

    private static final String NAME = HttpHeaders.AUTHORIZATION;

    public BearerTokenHeader(String token) {
        super(NAME, prefixBearer(token));
    }

    private static String prefixBearer(String token) {
        return "Bearer " + token;
    }

}
