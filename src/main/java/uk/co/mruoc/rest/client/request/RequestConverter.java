package uk.co.mruoc.rest.client.request;

import org.apache.http.HttpRequest;
import uk.co.mruoc.rest.client.BodyExtractor;
import uk.co.mruoc.rest.client.Method;
import uk.co.mruoc.rest.client.header.DefaultHeaders;
import uk.co.mruoc.rest.client.header.Headers;

public class RequestConverter {

    private final BodyExtractor bodyExtractor = new BodyExtractor();

    public Request toRequest(HttpRequest apacheRequest) {
        return new Request.RequestBuilder()
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

    private static Headers extractHeaders(HttpRequest request) {
        return new DefaultHeaders(request);
    }

}
