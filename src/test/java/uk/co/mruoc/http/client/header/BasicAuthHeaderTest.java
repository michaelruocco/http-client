package uk.co.mruoc.http.client.header;

import org.apache.http.HttpHeaders;
import org.junit.Test;
import uk.co.mruoc.http.client.header.BasicAuthHeader;
import uk.co.mruoc.http.client.header.Header;

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
