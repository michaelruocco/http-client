package uk.co.mruoc.http.client;


public class JwtAssertionHeader extends BasicHeader {

    private static final String JWT_ASSERTION = "X-JWT-Assertion";

    public JwtAssertionHeader(String application) {
        super(JWT_ASSERTION, application);
    }

}
