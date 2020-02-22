package uk.co.mruoc.rest.client;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.apache.http.HttpHeaders.CONTENT_TYPE;

public class ContentTypeExtractor {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractSimpleRestClient.class);
    private static final ContentType DEFAULT_CONTENT_TYPE = ContentType.TEXT_PLAIN;

    public static ContentType extract(HttpEntityEnclosingRequestBase request) {
        return Optional.ofNullable(request.getFirstHeader(CONTENT_TYPE))
                .map(ContentTypeExtractor::providedContentType)
                .orElseGet(ContentTypeExtractor::defaultContentType);
    }

    private static ContentType providedContentType(final Header contentType) {
        final String value = contentType.getValue();
        LOG.debug("returning provided content type {}", value);
        if (value.contains(";")) {
            final String[] subValues = value.split(";");
            LOG.debug("using charset {}", subValues[1]);
            return ContentType.create(subValues[0], subValues[1]);
        }
        return ContentType.create(value);
    }

    private static ContentType defaultContentType() {
        LOG.debug("content type not provided, returning default value {}", DEFAULT_CONTENT_TYPE);
        return DEFAULT_CONTENT_TYPE;
    }

}
