package uk.co.mruoc.rest.client.test;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import uk.co.mruoc.rest.client.HttpClientException;
import uk.co.mruoc.rest.client.Method;
import uk.co.mruoc.rest.client.request.Request;
import uk.co.mruoc.rest.client.request.RequestConverter;
import uk.co.mruoc.rest.client.header.DefaultHeaders;
import uk.co.mruoc.rest.client.header.Headers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FakeApacheHttpClient implements HttpClient {

    private final RequestConverter converter = new RequestConverter();
    private final List<Request> requests = new ArrayList<>();

    private HttpResponse response;
    private boolean throwIo;

    @Override
    public HttpResponse execute(HttpUriRequest rawRequest) throws IOException {
        requests.add(converter.toRequest(rawRequest));
        if (throwIo)
            throw new IOException();
        return response;
    }

    public void cannedResponse(int status) {
        cannedResponse(status, "");
    }

    public void cannedResponse(int status, String body) {
        cannedResponse(status, body, new DefaultHeaders());
    }

    public void cannedResponse(int status, String body, Headers headers) {
        response = makeApacheResponse(status, body, headers);
    }

    public List<Request> allRequests() {
        return requests;
    }

    public String lastRequestUri() {
        return lastRequest().getRequestUri();
    }

    public String lastRequestBody() {
        return lastRequest().getBody();
    }

    public Method lastRequestMethod() {
        return lastRequest().getMethod();
    }

    public String lastRequestHeader(String name) {
        return lastRequest().getHeader(name);
    }

    public Headers lastRequestHeaders() {
        return lastRequest().getHeaders();
    }

    public void throwsIoException() {
        this.throwIo = true;
    }

    @Override
    public HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException, ClientProtocolException {
        return null;
    }

    @Override
    public HttpResponse execute(HttpHost target, HttpRequest request) throws IOException, ClientProtocolException {
        return null;
    }

    @Override
    public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException, ClientProtocolException {
        return null;
    }

    @Override
    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return null;
    }

    @Override
    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
        return null;
    }

    @Override
    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return null;
    }

    @Override
    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
        return null;
    }

    @Override
    public HttpParams getParams() {
        return null;
    }

    @Override
    public ClientConnectionManager getConnectionManager() {
        return null;
    }

    public Request lastRequest() {
        return requests.get(requests.size() - 1);
    }

    private static BasicHttpResponse makeApacheResponse(int status, String body, Headers headers) {
        try {
            BasicHttpResponse apacheResponse = new BasicHttpResponse(createStatus(status));
            apacheResponse.setEntity(new StringEntity(body));
            setHeaders(apacheResponse, headers);
            return apacheResponse;
        } catch (UnsupportedEncodingException e) {
            throw new HttpClientException(e);
        }
    }

    private static BasicStatusLine createStatus(int statusCode) {
        return new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), statusCode, "OK");
    }

    private static void setHeaders(BasicHttpResponse apacheResponse, Headers headers) {
        for (String name : headers.getNames())
            apacheResponse.setHeader(name, headers.get(name));
    }

}
