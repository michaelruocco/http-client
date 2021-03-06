package uk.co.mruoc.rest.client.test;

import org.junit.Test;
import uk.co.mruoc.rest.client.header.DefaultHeaders;
import uk.co.mruoc.rest.client.header.Headers;
import uk.co.mruoc.rest.client.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class FakeMultipleResponseRestClientTest {

    private static final String ENDPOINT = "the-endpoint";
    private static final String ENTITY = "the-entity";

    private final FakeRestClient client = new FakeMultipleResponseRestClient();
    private final Headers headers = new DefaultHeaders();

    @Test
    public void getReturnsFakeResponsesInSequence() {
        client.cannedResponse(200);
        client.cannedResponse(300);
        client.cannedResponse(400);

        assertThat(client.get(ENDPOINT).getStatusCode()).isEqualTo(200);
        assertThat(client.get(ENDPOINT).getStatusCode()).isEqualTo(300);
        assertThat(client.get(ENDPOINT).getStatusCode()).isEqualTo(400);
    }

    @Test
    public void putReturnsFakeResponsesInSequence() {
        client.cannedResponse(200);
        client.cannedResponse(300);
        client.cannedResponse(400);

        assertThat(client.put(ENDPOINT, ENTITY).getStatusCode()).isEqualTo(200);
        assertThat(client.put(ENDPOINT, ENTITY).getStatusCode()).isEqualTo(300);
        assertThat(client.put(ENDPOINT, ENTITY).getStatusCode()).isEqualTo(400);
    }

    @Test
    public void patchReturnsFakeResponsesInSequence() {
        client.cannedResponse(200);
        client.cannedResponse(300);
        client.cannedResponse(400);

        assertThat(client.patch(ENDPOINT, ENTITY).getStatusCode()).isEqualTo(200);
        assertThat(client.patch(ENDPOINT, ENTITY).getStatusCode()).isEqualTo(300);
        assertThat(client.patch(ENDPOINT, ENTITY).getStatusCode()).isEqualTo(400);
    }

    @Test
    public void postReturnsFakeResponsesInSequence() {
        client.cannedResponse(200);
        client.cannedResponse(300);
        client.cannedResponse(400);

        assertThat(client.post(ENDPOINT, ENTITY).getStatusCode()).isEqualTo(200);
        assertThat(client.post(ENDPOINT, ENTITY).getStatusCode()).isEqualTo(300);
        assertThat(client.post(ENDPOINT, ENTITY).getStatusCode()).isEqualTo(400);
    }

    @Test
    public void deleteReturnsFakeResponsesInSequence() {
        client.cannedResponse(200);
        client.cannedResponse(300);
        client.cannedResponse(400);

        assertThat(client.delete(ENDPOINT).getStatusCode()).isEqualTo(200);
        assertThat(client.delete(ENDPOINT).getStatusCode()).isEqualTo(300);
        assertThat(client.delete(ENDPOINT).getStatusCode()).isEqualTo(400);
    }

    @Test
    public void recordsAllRequests() {
        Headers headers = new DefaultHeaders();
        headers.set("key1", "value1");
        client.cannedResponse(200);
        client.cannedResponse(300);
        client.cannedResponse(400);

        client.get(ENDPOINT);
        client.get(ENDPOINT);
        client.put(ENDPOINT, ENTITY, headers);

        assertThat(client.allRequests().size()).isEqualTo(3);
    }

    @Test
    public void recordsAllRequestDetails() {
        Headers headers = new DefaultHeaders();
        headers.set("key1", "value1");
        client.cannedResponse(200);
        client.cannedResponse(300);
        client.cannedResponse(400);

        client.get(ENDPOINT);
        client.get(ENDPOINT);
        client.patch(ENDPOINT, ENTITY, headers);

        assertThat(client.lastRequestMethod()).isEqualTo(Method.PATCH);
        assertThat(client.lastRequestUri()).isEqualTo(ENDPOINT);
        assertThat(client.lastRequestBody()).isEqualTo(ENTITY);
        assertThat(client.lastRequestHeaders().hasSameValues(headers)).isTrue();
        assertThat(client.lastRequestHeader("key1")).isEqualTo("value1");
    }

    @Test
    public void recordsAllRequestDetailsAsRequest() {
        Headers headers = new DefaultHeaders();
        headers.set("key1", "value1");
        client.cannedResponse(200);
        client.cannedResponse(300);
        client.cannedResponse(400);

        client.get(ENDPOINT);
        client.get(ENDPOINT);
        client.put(ENDPOINT, ENTITY, headers);

        assertThat(client.lastRequest().getMethod()).isEqualTo(Method.PUT);
        assertThat(client.lastRequest().getRequestUri()).isEqualTo(ENDPOINT);
        assertThat(client.lastRequest().getBody()).isEqualTo(ENTITY);
        assertThat(client.lastRequest().getHeaders().hasSameValues(headers)).isTrue();
        assertThat(client.lastRequest().getHeader("key1")).isEqualTo("value1");
    }

    @Test
    public void canAddFullResponse() {
        headers.set("key", "value");

        client.cannedResponse(200, "first");
        client.cannedResponse(300, "second", headers);

        assertEquals("first", client.get(ENDPOINT).getBody());
        assertEquals("value", client.get(ENDPOINT).getHeader("key"));
    }

}
