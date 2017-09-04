package uk.co.mruoc.http.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.io.UncheckedIOException;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public abstract class AbstractSimpleHttpClient extends BaseHttpClient {

    private final ResponseConverter converter = new ResponseConverter();
    private final HttpClient client;

    public AbstractSimpleHttpClient() {
        this(ApacheHttpClientFactory.build());
    }

    public AbstractSimpleHttpClient(int httpTimeout) {
        this(ApacheHttpClientFactory.build(httpTimeout));
    }

    public AbstractSimpleHttpClient(HttpClient client) {
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

    /*private void log(HttpRequestBase request) {
        URI uri = request.getURI();
        LOG.info("performing " + request.getMethod() + " on uri " + decode(uri.toString(), DEFAULT_ENCODING));
        LOG.debug("encoded uri is " + uri.toString());
    }

    private void log(Response response) {
        LOG.info("status code " + response.getStatusCode());
        LOG.info("body " + response.getBody());
        logHeaders(response);
    }

    private void logHeaders(Response response) {
        Collection<String> headersKeys = response.getHeaderKeys();
        headersKeys.forEach(h -> LOG.debug("response header " + h + " with value " + response.getHeader(h)));
    }*/

    private HttpPost createPost(String endpoint, String entity, Headers headers) {
        HttpPost post = new HttpPost(endpoint);
        post.setEntity(toJsonEntity(entity));
        addHeaders(post, headers);
        return post;
    }

    private HttpPut createPut(String endpoint, String entity, Headers headers) {
        HttpPut put = new HttpPut(endpoint);
        put.setEntity(toJsonEntity(entity));
        addHeaders(put, headers);
        return put;
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

    private void addHeaders(HttpRequest request, Headers headers) {
        for (String name : headers.getNames())
            request.setHeader(name, headers.get(name));
    }

    private HttpEntity toJsonEntity(String entity) {
        return new StringEntity(entity, APPLICATION_JSON);
    }

}
