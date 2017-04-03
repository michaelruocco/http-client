package uk.co.mruoc.http.client;

import org.apache.http.HttpHeaders;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BearerTokenHeaderTest {

    private static final String TOKEN = "abc123";

    private final Header header = new BearerTokenHeader(TOKEN);

    @Test
    public void shouldReturnNameAsAuthorization() {
        assertThat(header.getName()).isEqualTo(HttpHeaders.AUTHORIZATION);
    }

    @Test
    public void shouldPrefixTokenValueWithBearer() {
        assertThat(header.getValue()).isEqualTo("Bearer " + TOKEN);
    }

}
