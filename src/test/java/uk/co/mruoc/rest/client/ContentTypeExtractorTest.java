package uk.co.mruoc.rest.client;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.junit.Test;

import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ContentTypeExtractorTest {

    private final HttpEntityEnclosingRequestBase request = mock(HttpEntityEnclosingRequestBase.class);

    @Test
    public void shouldExtractProvidedContentTypeWithCharset() {
        final ContentType expectedContentType = ContentType.APPLICATION_JSON;
        given(request.getFirstHeader(CONTENT_TYPE)).willReturn(toHeader(expectedContentType));

        final ContentType contentType = ContentTypeExtractor.extract(request);

        assertThat(contentType.getMimeType()).isEqualTo(expectedContentType.getMimeType());
        assertThat(contentType.getCharset()).isEqualTo(expectedContentType.getCharset());
    }

    @Test
    public void shouldExtractDefaultContentTypAndCharsetIfNotProvided() {
        final ContentType expectedContentType = ContentType.TEXT_PLAIN;

        final ContentType contentType = ContentTypeExtractor.extract(request);

        assertThat(contentType.getMimeType()).isEqualTo(expectedContentType.getMimeType());
        assertThat(contentType.getCharset()).isEqualTo(expectedContentType.getCharset());
    }

    @Test
    public void shouldExtractProvidedContentTypeWithNullCharsetIfNotProvided() {
        final String expectedContentType = ContentType.APPLICATION_JSON.getMimeType();
        given(request.getFirstHeader(CONTENT_TYPE)).willReturn(toHeader(expectedContentType));

        final ContentType contentType = ContentTypeExtractor.extract(request);

        assertThat(contentType.getMimeType()).isEqualTo(expectedContentType);
        assertThat(contentType.getCharset()).isNull();
    }

    private static Header toHeader(final ContentType contentType) {
        final String value = String.format("%s;%s", contentType.getMimeType(), contentType.getCharset());
        return new BasicHeader(CONTENT_TYPE, value);
    }

    private static Header toHeader(final String value) {
        return new BasicHeader(CONTENT_TYPE, value);
    }

}
