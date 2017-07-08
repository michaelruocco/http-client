package uk.co.mruoc.http.client;

import org.apache.http.HttpHeaders;

public class BearerTokenHeader extends BasicHeader {

    public BearerTokenHeader(String token) {
        super(HeaderName.BEARER_TOKEN, prefixBearer(token));
    }

    private static String prefixBearer(String token) {
        return "Bearer " + token;
    }

}
