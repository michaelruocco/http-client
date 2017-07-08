package uk.co.mruoc.http.client;

public class AuthTokenHeader extends BasicHeader {

    public AuthTokenHeader(String token) {
        super(HeaderName.AUTH_TOKEN, token);
    }

}
