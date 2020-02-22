package uk.co.mruoc.rest.client.header;

import org.apache.http.HttpMessage;
import org.junit.Test;
import uk.co.mruoc.rest.client.ApacheHeaderBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DefaultHeadersTest {

    private final HttpMessage httpMessage = mock(HttpMessage.class);

    private final Headers headers = new DefaultHeaders();

    @Test(expected = HeaderNotFoundException.class)
    public void shouldThrowExceptionIfHeaderDoesNotExist() {
        headers.get("invalid");
    }

    @Test
    public void defaultSizeShouldBeZero() {
        assertThat(headers.size()).isEqualTo(0);
    }

    @Test
    public void shouldWriteHeaderValues() {
        headers.set("name", "value1");

        assertThat(headers.get("name")).isEqualTo("value1");
    }

    @Test
    public void sizeShouldIncrementWhenHeadersAdded() {
        headers.set("name", "value1");

        assertThat(headers.size()).isEqualTo(1);
    }

    @Test
    public void shouldOverwriteExistingHeaderValues() {
        headers.set("name", "value1");
        headers.set("name", "value2");

        assertThat(headers.get("name")).isEqualTo("value2");
    }

    @Test
    public void sizeShouldNotIncrementWhenHeaderValueOverwritten() {
        headers.set("name", "value1");
        headers.set("name", "value2");

        assertThat(headers.size()).isEqualTo(1);
    }

    @Test
    public void shouldReturnHeaderKeys() {
        headers.set("name1", "value1");
        headers.set("name2", "value2");

        assertThat(headers.getNames()).containsExactlyInAnyOrder("name1", "name2");
    }

    @Test
    public void shouldPopulateHeadersFromHttpMessage() {
        org.apache.http.Header[] apacheHeaders = givenHttpMessageWillReturnHeaders();

        Headers newHeaders = new DefaultHeaders(httpMessage);
        newHeaders.setHeaders(httpMessage);

        assertThat(newHeaders.get(apacheHeaders[0].getName())).isEqualTo(apacheHeaders[0].getValue());
        assertThat(newHeaders.get(apacheHeaders[1].getName())).isEqualTo(apacheHeaders[1].getValue());
    }

    @Test
    public void shouldSetCustomBasicHeader() {
        Header header = new SimpleHeader("custom-name", "custom-value");

        headers.set(header);

        assertThat(headers.get(header.getName())).isEqualTo(header.getValue());
    }

    @Test
    public void shouldReturnFalseIfDoesNotContainHeader() {
        assertThat(headers.contains("custom-name")).isFalse();
    }

    @Test
    public void shouldReturnTrueIfContainsHeader() {
        Header header = new SimpleHeader("custom-name", "custom-value");

        headers.set(header);

        assertThat(headers.contains(header.getName())).isTrue();
    }

    @Test
    public void shouldSetBearerToken() {
        String token = "my-token";

        headers.setBearerToken(token);

        assertThat(headers.getAuthorization()).isEqualTo("Bearer " + token);
    }

    @Test
    public void shouldHaveBearerTokenIfSet() {
        String token = "my-token";

        headers.setBearerToken(token);

        assertThat(headers.hasAuthorization()).isTrue();
    }

    @Test
    public void shouldSetContentType() {
        String contentType = "application/json";

        headers.setContentType(contentType);

        assertThat(headers.getContentType()).isEqualTo(contentType);
    }

    @Test
    public void shouldNotHaveContentTypeByDefault() {
        assertThat(headers.hasContentType()).isFalse();
    }

    @Test
    public void shouldHaveContentTypeIfSet() {
        String contentType = "application/json";

        headers.setContentType(contentType);

        assertThat(headers.hasContentType()).isTrue();
    }

    @Test
    public void shouldSetAccept() {
        String accept = "application/json";

        headers.setAccept(accept);

        assertThat(headers.getAccept()).isEqualTo(accept);
    }

    @Test
    public void shouldNotHaveAcceptByDefault() {
        assertThat(headers.hasAccept()).isFalse();
    }

    @Test
    public void shouldHaveAcceptIfSet() {
        String accept = "application/json";

        headers.setAccept(accept);

        assertThat(headers.hasAccept()).isTrue();
    }

    @Test
    public void shouldSetBasicAuth() {
        String authToken = "my-token";

        headers.setBasicAuth(authToken);

        assertThat(headers.getAuthorization()).isEqualTo("Basic " + authToken);
    }

    @Test
    public void shouldNotHaveAuthorizationByDefault() {
        assertThat(headers.hasAuthorization()).isFalse();
    }

    @Test
    public void shouldHaveAuthorizationIfBasicAuthSet() {
        String authToken = "my-token";

        headers.setBasicAuth(authToken);

        assertThat(headers.hasAuthorization()).isTrue();
    }

    private org.apache.http.Header[] givenHttpMessageWillReturnHeaders() {
        org.apache.http.Header[] headers = buildApacheHeaders();
        given(httpMessage.getAllHeaders()).willReturn(headers);
        return headers;
    }

    private static org.apache.http.Header[] buildApacheHeaders() {
        org.apache.http.Header header1 = ApacheHeaderBuilder.build("headerName1", "headerValue1");
        org.apache.http.Header header2 = ApacheHeaderBuilder.build("headerName2", "headerValue2");
        return new org.apache.http.Header[] { header1, header2 };
    }

}
