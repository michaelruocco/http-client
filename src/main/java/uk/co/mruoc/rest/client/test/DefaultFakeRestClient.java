package uk.co.mruoc.rest.client.test;

import org.apache.http.client.methods.HttpRequestBase;
import uk.co.mruoc.rest.client.Method;
import uk.co.mruoc.rest.client.request.Request;
import uk.co.mruoc.rest.client.response.Response;
import uk.co.mruoc.rest.client.SimpleRestClient;
import uk.co.mruoc.rest.client.header.Headers;

import java.util.List;

public class DefaultFakeRestClient extends SimpleRestClient implements FakeRestClient {

    private RuntimeException exception;
    private FakeApacheHttpClient fakeHttp;

    public DefaultFakeRestClient() {
        this(new FakeApacheHttpClient());
    }

    private DefaultFakeRestClient(FakeApacheHttpClient fakeHttp) {
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
