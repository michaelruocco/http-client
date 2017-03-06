package uk.co.mruoc.http.client;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;

import static uk.co.mruoc.http.client.Request.*;

public class RequestConverter {

    private static final Logger LOG = LoggerFactory.getLogger(Headers.class);

    public Request toRequest(HttpRequest apacheRequest) {
        return new RequestBuilder()
                .setUri(extractUri(apacheRequest))
                .setMethod(extractMethod(apacheRequest))
                .setBody(extractBody(apacheRequest))
                .setHeaders(extractHeaders(apacheRequest))
                .build();
    }

    private static String extractUri(HttpRequest request) {
        return request.getRequestLine().getUri();
    }

    private static Method extractMethod(HttpRequest request) {
        return Method.valueOf(request.getRequestLine().getMethod());
    }

    private static String extractBody(HttpRequest request) {
        try {
            HttpEntityEnclosingRequest entityRequest = (HttpEntityEnclosingRequest) request;
            return EntityUtils.toString(entityRequest.getEntity());
        } catch (ClassCastException e) {
            LOG.debug("apache request does not have a body available", e);
            return "";
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static Headers extractHeaders(HttpRequest request) {
        return new Headers(request);
    }

}
