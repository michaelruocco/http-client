package uk.co.mruoc.rest.client.header;

import org.apache.http.HttpHeaders;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicAuthHeaderTest {

    private static final String TOKEN = "abc123";

    private final Header header = new BasicAuthHeader(TOKEN);

    @Test
    public void shouldReturnNameAsAuthorization() {
        assertThat(header.getName()).isEqualTo(HttpHeaders.AUTHORIZATION);
    }

    @Test
    public void shouldPrefixTokenValueWithBasic() {
        assertThat(header.getValue()).isEqualTo("Basic " + TOKEN);
    }

}
