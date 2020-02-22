package uk.co.mruoc.http.client.header;

public class BasicAuthHeader extends SimpleHeader {

    public BasicAuthHeader(String token) {
        super(CommonHeaderName.AUTHORIZATION, prefixBasic(token));
    }

    private static String prefixBasic(String token) {
        return "Basic " + token;
    }

}
