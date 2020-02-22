package uk.co.mruoc.http.client.test;

import org.apache.http.client.methods.HttpRequestBase;
import uk.co.mruoc.http.client.*;
import uk.co.mruoc.http.client.header.DefaultHeaders;
import uk.co.mruoc.http.client.header.Headers;

import java.util.List;

public class DefaultFakeHttpClient extends SimpleHttpClient implements FakeHttpClient {

    private RuntimeException exception;
    private FakeApacheHttpClient fakeHttp;

    public DefaultFakeHttpClient() {
        this(new FakeApacheHttpClient());
    }

    private DefaultFakeHttpClient(FakeApacheHttpClient fakeHttp) {
        super(fakeHttp);
        this.fakeHttp = fakeHttp;
    }

    @Override
    public List<Request> allRequests() {
        return fakeHttp.allRequests();
    }

    @Override
    public Method lastRequestMethod() {
        return fakeHttp.lastRequestMethod();
    }

    @Override
    public String lastRequestBody() {
        return fakeHttp.lastRequestBody();
    }

    @Override
    public String lastRequestHeader(String name) {
        return fakeHttp.lastRequestHeader(name);
    }

    @Override
    public Headers lastRequestHeaders() {
        return fakeHttp.lastRequestHeaders();
    }

    @Override
    public Request lastRequest() {
        return fakeHttp.lastRequest();
    }

    @Override
    public String lastRequestUri() {
        return fakeHttp.lastRequestUri();
    }

    @Override
    public void cannedResponse(int status) {
        fakeHttp.cannedResponse(status);
    }

    @Override
    public void cannedResponse(int status, String entity) {
        fakeHttp.cannedResponse(status, entity);
    }

    @Override
    public void cannedResponse(int status, String entity, Headers headers) {
        fakeHttp.cannedResponse(status, entity, headers);
    }

    @Override
    public void cannedResponse(Response response) {
        fakeHttp.cannedResponse(response.getStatusCode(), response.getBody(), response.getHeaders());
    }

    @Override
    public void throwsException(RuntimeException exception) {
        this.exception = exception;
    }

    @Override
    public void throwsIoException() {
        fakeHttp.throwsIoException();
    }

    @Override
    protected Response execute(HttpRequestBase request) {
        if (exception == null)
            return super.execute(request);
        throw exception;
    }

}
