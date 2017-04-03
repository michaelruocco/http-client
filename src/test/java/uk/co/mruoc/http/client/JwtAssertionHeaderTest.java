package uk.co.mruoc.http.client;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtAssertionHeaderTest {

    private static final String JWT_ASSERTION = "X-JWT-Assertion";
    private static final String APPLICATION = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik";

    private final Header header = new JwtAssertionHeader(APPLICATION);

    @Test
    public void shouldReturnNameAsJwtAssertion() {
        assertThat(header.getName()).isEqualTo(JWT_ASSERTION);
    }

    @Test
    public void shouldReturnValueAsApplicationPassed() {
        assertThat(header.getValue()).isEqualTo(APPLICATION);
    }

}
