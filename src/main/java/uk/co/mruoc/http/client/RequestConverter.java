package uk.co.mruoc.http.client;

import org.apache.http.HttpRequest;
import uk.co.mruoc.http.client.header.DefaultHeaders;
import uk.co.mruoc.http.client.header.Headers;

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
