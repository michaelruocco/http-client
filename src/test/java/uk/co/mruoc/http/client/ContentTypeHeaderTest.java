package uk.co.mruoc.http.client;

import org.apache.http.HttpHeaders;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContentTypeHeaderTest {

    private static final String APPLICATION_JSON = "application/json";

    private final Header header = new ContentTypeHeader(APPLICATION_JSON);

    @Test
    public void shouldReturnNameAsContentType() {
        assertThat(header.getName()).isEqualTo(HttpHeaders.CONTENT_TYPE);
    }

    @Test
    public void shouldReturnValueAsContentTypePassed() {
        assertThat(header.getValue()).isEqualTo(APPLICATION_JSON);
    }

}
