package uk.co.mruoc.rest.client.header;

public class BasicAuthHeader extends SimpleHeader {

    public BasicAuthHeader(String token) {
        super(CommonHeaderName.AUTHORIZATION, prefixBasic(token));
    }

    private static String prefixBasic(String token) {
        return "Basic " + token;
    }

}
