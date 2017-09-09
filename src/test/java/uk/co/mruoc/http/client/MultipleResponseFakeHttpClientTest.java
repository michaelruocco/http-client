package uk.co.mruoc.http.client;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultipleResponseFakeHttpClientTest {

    private static final String ENDPOINT = "the-endpoint";
    private static final String ENTITY = "the-entity";

    private final MultipleResponseFakeHttpClient client = new MultipleResponseFakeHttpClient();
    private final Headers headers = new Headers();

    @Test
    public void getReturnsFakeResponsesInSequence() {
        client.addResponse(200);
        client.addResponse(300);
        client.addResponse(400);

        assertEquals(200, client.get(ENDPOINT).getStatusCode());
        assertEquals(300, client.get(ENDPOINT).getStatusCode());
        assertEquals(400, client.get(ENDPOINT).getStatusCode());
    }

    @Test
    public void putReturnsFakeResponsesInSequence() {
        client.addResponse(200);
        client.addResponse(300);
        client.addResponse(400);

        assertEquals(200, client.put(ENDPOINT, ENTITY).getStatusCode());
        assertEquals(300, client.put(ENDPOINT, ENTITY).getStatusCode());
        assertEquals(400, client.put(ENDPOINT, ENTITY).getStatusCode());
    }

    @Test
    public void postReturnsFakeResponsesInSequence() {
        client.addResponse(200);
        client.addResponse(300);
        client.addResponse(400);

        assertEquals(200, client.post(ENDPOINT, ENTITY).getStatusCode());
        assertEquals(300, client.post(ENDPOINT, ENTITY).getStatusCode());
        assertEquals(400, client.post(ENDPOINT, ENTITY).getStatusCode());
    }

    @Test
    public void recordsRequests() {
        client.addResponse(200);
        client.addResponse(300);
        client.addResponse(400);

        client.get(ENDPOINT);
        client.get(ENDPOINT);
        client.delete(ENDPOINT);

        assertEquals(3, client.allRequests().size());
        assertEquals(Method.DELETE, client.lastRequest().getMethod());
    }

    @Test
    public void canAddFullResponse() {
        headers.add("key", "value");

        client.addResponse(200, "first");
        client.addResponse(300, "second", headers);

        assertEquals("first", client.get(ENDPOINT).getBody());
        assertEquals("value", client.get(ENDPOINT).getHeader("key"));
    }

}