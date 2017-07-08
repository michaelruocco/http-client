package uk.co.mruoc.http.client;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthTokenHeaderTest {

    private static final String TOKEN = "my-token";

    private final Header header = new AuthTokenHeader(TOKEN);

    @Test
    public void shouldReturnNameAsAuthToken() {
        assertThat(header.getName()).isEqualTo("X-Auth-Token");
    }

    @Test
    public void shouldReturnValueAsTokenPassed() {
        assertThat(header.getValue()).isEqualTo(TOKEN);
    }

}
