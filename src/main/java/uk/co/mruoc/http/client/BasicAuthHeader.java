package uk.co.mruoc.http.client;

public class BasicAuthHeader extends BasicHeader {

    public BasicAuthHeader(String token) {
        super(HeaderName.BEARER_TOKEN, prefixBasic(token));
    }

    private static String prefixBasic(String token) {
        return "Basic " + token;
    }

}
