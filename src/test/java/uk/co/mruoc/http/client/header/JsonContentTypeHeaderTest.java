package uk.co.mruoc.http.client.header;

import org.apache.http.HttpHeaders;
import org.junit.Test;
import uk.co.mruoc.http.client.header.Header;
import uk.co.mruoc.http.client.header.JsonContentTypeHeader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonContentTypeHeaderTest {

    private static final String APPLICATION_JSON = "application/json";

    private final Header header = new JsonContentTypeHeader();

    @Test
    public void shouldReturnNameAsContentType() {
        assertThat(header.getName()).isEqualTo(HttpHeaders.CONTENT_TYPE);
    }

    @Test
    public void shouldReturnValueAsContentTypePassed() {
        assertThat(header.getValue()).isEqualTo(APPLICATION_JSON);
    }

}
