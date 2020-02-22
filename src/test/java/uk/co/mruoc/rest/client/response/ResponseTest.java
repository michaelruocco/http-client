package uk.co.mruoc.rest.client.response;

import org.junit.Test;
import uk.co.mruoc.rest.client.response.Response.ResponseBuilder;
import uk.co.mruoc.rest.client.header.DefaultHeaders;
import uk.co.mruoc.rest.client.header.Headers;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseTest {

    private final ResponseBuilder builder = new ResponseBuilder();

    @Test
    public void shouldReturnStatusCode() {
        int statusCode = 200;

        Response response = responseWithStatusCode(statusCode);

        assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }

    @Test
    public void shouldReturnBody() {
        String body = "my-body";

        Response response = builder.setBody(body).build();

        assertThat(response.getBody()).isEqualTo(body);
    }

    @Test
    public void shouldReturnHeaderValue() {
        Headers headers = new DefaultHeaders();
        headers.set("my-header", "some-value");

        Response response = builder.setHeaders(headers).build();

        assertThat(response.getHeader("my-header")).isEqualTo("some-value");
    }

    @Test
    public void shouldReturnHeaderKeys() {
        Headers headers = new DefaultHeaders();
        headers.set("my-header-1", "some-value");
        headers.set("my-header-2", "some-value");

        Response response = builder.setHeaders(headers).build();

        assertThat(response.getHeaderKeys()).containsExactly("my-header-1", "my-header-2");
    }

    @Test
    public void shouldReturnHeaders() {
        Headers headers = new DefaultHeaders();

        Response response = builder.setHeaders(headers).build();

        assertThat(response.getHeaders()).isEqualTo(headers);
    }

    @Test
    public void shouldReturnHeadersAsMap() {
        Headers headers = new DefaultHeaders();

        Response response = builder.setHeaders(headers).build();

        assertThat(response.getHeadersAsMap()).isEmpty();
    }

    @Test
    public void shouldBeInOneHundredRange() {
        assertThat(responseWithStatusCode(99).is1xx()).isFalse();

        assertThat(responseWithStatusCode(100).is1xx()).isTrue();
        assertThat(responseWithStatusCode(199).is1xx()).isTrue();

        assertThat(responseWithStatusCode(200).is1xx()).isFalse();
    }

    @Test
    public void shouldBeInTwoHundredRange() {
        assertThat(responseWithStatusCode(199).is2xx()).isFalse();

        assertThat(responseWithStatusCode(200).is2xx()).isTrue();
        assertThat(responseWithStatusCode(299).is2xx()).isTrue();

        assertThat(responseWithStatusCode(300).is2xx()).isFalse();
    }

    @Test
    public void shouldBeInThreeHundredRange() {
        assertThat(responseWithStatusCode(299).is3xx()).isFalse();

        assertThat(responseWithStatusCode(300).is3xx()).isTrue();
        assertThat(responseWithStatusCode(399).is3xx()).isTrue();

        assertThat(responseWithStatusCode(400).is3xx()).isFalse();
    }

    @Test
    public void shouldBeInFourHundredRange() {
        assertThat(responseWithStatusCode(399).is4xx()).isFalse();

        assertThat(responseWithStatusCode(400).is4xx()).isTrue();
        assertThat(responseWithStatusCode(499).is4xx()).isTrue();

        assertThat(responseWithStatusCode(500).is4xx()).isFalse();
    }

    @Test
    public void shouldBeInFiveHundredRange() {
        assertThat(responseWithStatusCode(499).is5xx()).isFalse();

        assertThat(responseWithStatusCode(500).is5xx()).isTrue();
        assertThat(responseWithStatusCode(599).is5xx()).isTrue();

        assertThat(responseWithStatusCode(600).is5xx()).isFalse();
    }

    private Response responseWithStatusCode(int statusCode) {
        return builder.setStatusCode(statusCode).build();
    }

}
