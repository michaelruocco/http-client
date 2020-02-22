package uk.co.mruoc.rest.client;

import uk.co.mruoc.rest.client.header.DefaultHeaders;
import uk.co.mruoc.rest.client.header.Headers;
import uk.co.mruoc.rest.client.response.Response;

public abstract class BaseRestClient implements RestClient {

    @Override
    public Response post(String endpoint, String entity) {
        return post(endpoint, entity, new DefaultHeaders());
    }

    @Override
    public abstract Response post(String endpoint, String entity, Headers headers);

    @Override
    public Response put(String endpoint, String entity) {
        return put(endpoint, entity, new DefaultHeaders());
    }

    @Override
    public abstract Response put(String endpoint, String entity, Headers headers);

    @Override
    public Response patch(String endpoint, String entity) {
        return put(endpoint, entity, new DefaultHeaders());
    }

    @Override
    public abstract Response patch(String endpoint, String entity, Headers headers);

    @Override
    public Response get(String endpoint) {
        return get(endpoint, new DefaultHeaders());
    }

    @Override
    public abstract Response get(String endpoint, Headers headers);

    @Override
    public Response delete(String endpoint) {
        return delete(endpoint, new DefaultHeaders());
    }

    @Override
    public abstract Response delete(String endpoint, Headers headers);

}
