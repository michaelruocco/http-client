package uk.co.mruoc.rest.client.header;

import org.junit.Test;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationJsonHeadersTest {

    private final Headers headers = new ApplicationJsonHeaders();

    @Test
    public void defaultSizeShouldBeTwo() {
        assertThat(headers.size()).isEqualTo(2);
    }

    @Test
    public void shouldReturnHeaderKeys() {
        assertThat(headers.getNames()).containsExactlyInAnyOrder(CONTENT_TYPE, ACCEPT);
    }

    @Test
    public void shouldHaveApplicationJsonContentTypeByDefault() {
        assertThat(headers.getContentType()).isEqualTo(APPLICATION_JSON.getMimeType());
    }

    @Test
    public void shouldHaveApplicationJsonAcceptByDefault() {
        assertThat(headers.getAccept()).isEqualTo(APPLICATION_JSON.getMimeType());
    }

    @Test
    public void shouldSetContentType() {
        String contentType = "abc/123";

        headers.setContentType(contentType);

        assertThat(headers.getContentType()).isEqualTo(contentType);
    }

    @Test
    public void shouldSetAccept() {
        String accept = "abc/123";

        headers.setAccept(accept);

        assertThat(headers.getAccept()).isEqualTo(accept);
    }

}
