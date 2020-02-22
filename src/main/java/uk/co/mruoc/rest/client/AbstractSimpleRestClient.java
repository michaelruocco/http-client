package uk.co.mruoc.rest.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import uk.co.mruoc.rest.client.header.Headers;
import uk.co.mruoc.rest.client.response.Response;
import uk.co.mruoc.rest.client.response.ResponseConverter;

import java.io.IOException;
import java.io.UncheckedIOException;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public abstract class AbstractSimpleRestClient extends BaseRestClient {

    private final ResponseConverter converter = new ResponseConverter();
    private final HttpClient client;

    public AbstractSimpleRestClient(HttpClient client) {
        this.client = client;
    }

    @Override
    public Response post(String endpoint, String entity, Headers headers) {
        HttpPost post = createPost(endpoint, entity, headers);
        return execute(post);
    }

    @Override
    public Response put(String endpoint, String entity, Headers headers) {
        HttpPut put = createPut(endpoint, entity, headers);
        return execute(put);
    }

    @Override
    public Response patch(String endpoint, String entity, Headers headers) {
        HttpPatch patch = createPatch(endpoint, entity, headers);
        return execute(patch);
    }

    @Override
    public Response get(String endpoint, Headers headers) {
        HttpGet get = createGet(endpoint, headers);
        return execute(get);
    }

    @Override
    public Response delete(String endpoint, Headers headers) {
        HttpDelete delete = createDelete(endpoint, headers);
        return execute(delete);
    }

    protected Response execute(HttpRequestBase request) {
        try {
            log(request);
            HttpResponse rawResponse = client.execute(request);
            Response response = converter.toResponse(rawResponse);
            log(response);
            return response;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            request.releaseConnection();
        }
    }

    protected abstract void log(HttpRequestBase request);

    protected abstract void log(Response response);

    private HttpPost createPost(String endpoint, String entity, Headers headers) {
        HttpPost post = new HttpPost(endpoint);
        setEntity(post, entity);
        addHeaders(post, headers);
        return post;
    }

    private HttpPut createPut(String endpoint, String entity, Headers headers) {
        HttpPut put = new HttpPut(endpoint);
        setEntity(put, entity);
        addHeaders(put, headers);
        return put;
    }

    private HttpPatch createPatch(String endpoint, String entity, Headers headers) {
        HttpPatch patch = new HttpPatch(endpoint);
        setEntity(patch, entity);
        addHeaders(patch, headers);
        return patch;
    }

    private HttpGet createGet(String endpoint, Headers headers) {
        HttpGet get = new HttpGet(endpoint);
        headers.add(ACCEPT, APPLICATION_JSON.getMimeType());
        addHeaders(get, headers);
        return get;
    }

    private HttpDelete createDelete(String endpoint, Headers headers) {
        HttpDelete delete = new HttpDelete(endpoint);
        addHeaders(delete, headers);
        return delete;
    }

    private void setEntity(HttpEntityEnclosingRequestBase request, String entity) {
        request.setEntity(toJsonEntity(entity));
    }

    private void addHeaders(HttpRequest request, Headers headers) {
        for (String name : headers.getNames())
            request.setHeader(name, headers.get(name));
    }

    private HttpEntity toJsonEntity(String entity) {
        return new StringEntity(entity, APPLICATION_JSON);
    }

}
