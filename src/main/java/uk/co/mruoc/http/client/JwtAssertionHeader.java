package uk.co.mruoc.http.client;


public class JwtAssertionHeader extends BasicHeader {

    public JwtAssertionHeader(String application) {
        super(HeaderName.JWT_ASSERTION, application);
    }

}
