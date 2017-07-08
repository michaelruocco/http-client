package uk.co.mruoc.http.client;

import org.apache.http.HttpMessage;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class HeadersTest {

    private final HttpMessage httpMessage = mock(HttpMessage.class);

    private final Headers headers = new Headers();

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
        headers.add("name", "value1");

        assertThat(headers.get("name")).isEqualTo("value1");
    }

    @Test
    public void sizeShouldIncrementWhenHeadersAdded() {
        headers.add("name", "value1");

        assertThat(headers.size()).isEqualTo(1);
    }

    @Test
    public void shouldOverwriteExistingHeaderValues() {
        headers.add("name", "value1");
        headers.add("name", "value2");

        assertThat(headers.get("name")).isEqualTo("value2");
    }

    @Test
    public void sizeShouldNotIncrementWhenHeaderValueOverwritten() {
        headers.add("name", "value1");
        headers.add("name", "value2");

        assertThat(headers.size()).isEqualTo(1);
    }

    @Test
    public void shouldReturnHeaderKeys() {
        headers.add("name1", "value1");
        headers.add("name2", "value2");

        assertThat(headers.getNames()).containsExactlyInAnyOrder("name1", "name2");
    }

    @Test
    public void headerExistsShouldReturnFalseIfHeaderDoesNotExist() {
        assertThat(headers.headerExists("name1")).isFalse();
    }

    @Test
    public void headerExistsShouldReturnTrueIfHeaderExists() {
        headers.add("name1", "value1");

        assertThat(headers.headerExists("name1")).isTrue();
    }

    @Test
    public void shouldPopulateHeadersFromHttpMessage() {
        org.apache.http.Header[] apacheHeaders = givenHttpMessageWillReturnHeaders();

        Headers newHeaders = new Headers(httpMessage);
        newHeaders.addHeaders(httpMessage);

        assertThat(newHeaders.get(apacheHeaders[0].getName())).isEqualTo(apacheHeaders[0].getValue());
        assertThat(newHeaders.get(apacheHeaders[1].getName())).isEqualTo(apacheHeaders[1].getValue());
    }

    @Test
    public void shouldAddCustomBasicHeader() {
        Header header = new BasicHeader("custom-name", "custom-value");

        headers.add(header);

        assertThat(headers.get(header.getName())).isEqualTo(header.getValue());
    }

    @Test
    public void shouldReturnFalseIfDoesNotContainHeader() {
        assertThat(headers.contains("custom-name")).isFalse();
    }

    @Test
    public void shouldReturnTrueIfContainsHeader() {
        Header header = new BasicHeader("custom-name", "custom-value");

        headers.add(header);

        assertThat(headers.contains(header.getName())).isTrue();
    }

    @Test
    public void shouldAddBearerToken() {
        String token = "my-token";

        headers.addBearerToken(token);

        assertThat(headers.getBearerToken()).isEqualTo("Bearer " + token);
    }

    @Test
    public void shouldNotHaveBearerTokenByDefault() {
        assertThat(headers.hasBearerToken()).isFalse();
    }

    @Test
    public void shouldHaveBearerTokenIfSet() {
        String token = "my-token";

        headers.addBearerToken(token);

        assertThat(headers.hasBearerToken()).isTrue();
    }

    @Test
    public void shouldAddJwtAssertion() {
        String application = "my-application";

        headers.addJwtAssertion(application);

        assertThat(headers.getJwtAssertion()).isEqualTo(application);
    }

    @Test
    public void shouldNotHaveJwtAssertionByDefault() {
        assertThat(headers.hasJwtAssertion()).isFalse();
    }

    @Test
    public void shouldHaveJwtAssertionIfSet() {
        String application = "my-application";

        headers.addJwtAssertion(application);

        assertThat(headers.hasJwtAssertion()).isTrue();
    }

    @Test
    public void shouldAddContentType() {
        String contentType = "application/json";

        headers.addContentType(contentType);

        assertThat(headers.getContentType()).isEqualTo(contentType);
    }

    @Test
    public void shouldNotHaveContentTypeByDefault() {
        assertThat(headers.hasContentType()).isFalse();
    }

    @Test
    public void shouldHaveContentTypeIfSet() {
        String contentType = "application/json";

        headers.addContentType(contentType);

        assertThat(headers.hasContentType()).isTrue();
    }

    @Test
    public void shouldAddAccept() {
        String accept = "application/json";

        headers.addAccept(accept);

        assertThat(headers.getAccept()).isEqualTo(accept);
    }

    @Test
    public void shouldNotHaveAcceptByDefault() {
        assertThat(headers.hasAccept()).isFalse();
    }

    @Test
    public void shouldHaveAcceptIfSet() {
        String accept = "application/json";

        headers.addAccept(accept);

        assertThat(headers.hasAccept()).isTrue();
    }

    @Test
    public void shouldAddAuthToken() {
        String authToken = "my-token";

        headers.addAuthToken(authToken);

        assertThat(headers.getAuthToken()).isEqualTo(authToken);
    }

    @Test
    public void shouldNotHaveAuthTokenByDefault() {
        assertThat(headers.hasAuthToken()).isFalse();
    }

    @Test
    public void shouldHaveAuthTokenIfSet() {
        String authToken = "my-token";

        headers.addAuthToken(authToken);

        assertThat(headers.hasAuthToken()).isTrue();
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
