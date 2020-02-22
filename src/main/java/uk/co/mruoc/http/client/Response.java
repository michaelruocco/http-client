package uk.co.mruoc.http.client;

import uk.co.mruoc.http.client.header.DefaultHeaders;
import uk.co.mruoc.http.client.header.Headers;

import java.util.Collection;

public class Response {

    private final int statusCode;
    private final String body;
    private final Headers headers;

    private Response(ResponseBuilder builder) {
        this.statusCode = builder.statusCode;
        this.body = builder.body;
        this.headers = builder.headers;
    }

    public String getBody() {
        return body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

    public Collection<String> getHeaderKeys() {
        return headers.getNames();
    }

    public Headers getHeaders() {
        return headers;
    }

    public boolean is1xx() {
        return statusBetween(99, 200);
    }

    public boolean is2xx() {
        return statusBetween(199, 300);
    }

    public boolean is3xx() {
        return statusBetween(299, 400);
    }

    public boolean is4xx() {
        return statusBetween(399, 500);
    }

    public boolean is5xx() {
        return statusBetween(499, 600);
    }

    private boolean statusBetween(int start, int end) {
        return statusCode > start && statusCode < end;
    }

    public static class ResponseBuilder {

        private int statusCode;
        private String body = "";
        private Headers headers = new DefaultHeaders();

        public ResponseBuilder setStatusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ResponseBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public ResponseBuilder setHeaders(Headers headers) {
            this.headers = headers;
            return this;
        }

        public Response build() {
            return new Response(this);
        }

    }

}

