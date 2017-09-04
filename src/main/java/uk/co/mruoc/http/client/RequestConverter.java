package uk.co.mruoc.http.client;

import org.apache.http.HttpRequest;

import static uk.co.mruoc.http.client.Request.*;

public class RequestConverter {

    private final BodyExtractor bodyExtractor = new BodyExtractor();

    public Request toRequest(HttpRequest apacheRequest) {
        return new RequestBuilder()
                .setUri(extractUri(apacheRequest))
                .setMethod(extractMethod(apacheRequest))
                .setBody(bodyExtractor.extract(apacheRequest))
                .setHeaders(extractHeaders(apacheRequest))
                .build();
    }

    private static String extractUri(HttpRequest request) {
        return request.getRequestLine().getUri();
    }

    private static Method extractMethod(HttpRequest request) {
        return Method.valueOf(request.getRequestLine().getMethod());
    }

    /*private static String extractBody(HttpRequest request) {
        try {
            HttpEntityEnclosingRequest entityRequest = (HttpEntityEnclosingRequest) request;
            return EntityUtils.toString(entityRequest.getEntity());
        } catch (ClassCastException e) {
            LOG.debug("apache request does not have a body available", e);
            return "";
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }*/

    private static Headers extractHeaders(HttpRequest request) {
        return new Headers(request);
    }

}
