package uk.co.mruoc.http.client;

import uk.co.mruoc.http.client.Response.ResponseBuilder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class MultipleResponseFakeHttpClient extends BaseHttpClient {

    private final Deque<Response> responseStack = new ArrayDeque<>();
    private final FakeHttpClient client = new FakeHttpClient();

    @Override
    public Response post(String endpoint, String entity, Headers headers) {
        client.cannedResponse(responseStack.pop());
        return client.post(endpoint, entity, headers);
    }

    @Override
    public Response put(String endpoint, String entity, Headers headers) {
        client.cannedResponse(responseStack.pop());
        return client.put(endpoint, entity, headers);
    }

    @Override
    public Response get(String endpoint, Headers headers) {
        client.cannedResponse(responseStack.pop());
        return client.get(endpoint, headers);
    }

    @Override
    public Response delete(String endpoint, Headers headers) {
        client.cannedResponse(responseStack.pop());
        return client.delete(endpoint, headers);
    }

    public void addResponse(int status) {
        Response response = new ResponseBuilder()
                .setStatusCode(status)
                .build();
        responseStack.addLast(response);
    }

    public void addResponse(int status, String body) {
        Response response = new ResponseBuilder()
                .setStatusCode(status)
                .setBody(body)
                .build();
        responseStack.addLast(response);
    }

    public void addResponse(int status, String body, Headers headers) {
        Response response = new ResponseBuilder()
                .setStatusCode(status)
                .setBody(body)
                .setHeaders(headers)
                .build();
        responseStack.addLast(response);
    }

    public List<Request> allRequests() {
        return client.allRequests();
    }

    public Request lastRequest() {
        return client.lastRequest();
    }

}
