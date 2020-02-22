package uk.co.mruoc.rest.client.header;

public class BearerTokenHeader extends SimpleHeader {

    public BearerTokenHeader(String token) {
        super(CommonHeaderName.AUTHORIZATION, prefixBearer(token));
    }

    private static String prefixBearer(String token) {
        return "Bearer " + token;
    }

}
