package uk.co.mruoc.http.client;

import org.apache.http.HttpMessage;

import java.util.*;

import static uk.co.mruoc.http.client.HeaderName.*;
import static uk.co.mruoc.http.client.HeaderName.BEARER_TOKEN;
import static uk.co.mruoc.http.client.HeaderName.JWT_ASSERTION;

public class Headers {

    private final Map<String, String> values = new HashMap<>();

    public Headers() {
        // intentionally blank
    }

    public Headers(HttpMessage message) {
        addHeaders(message);
    }

    public void addHeaders(HttpMessage message) {
        addHeaders(message.getAllHeaders());
    }

    public void addHeaders(org.apache.http.Header... headers) {
        Arrays.stream(headers).forEach(h -> add(h.getName(), h.getValue()));
    }

    public void add(Header header) {
        add(header.getName(), header.getValue());
    }

    public void add(String name, String value) {
        values.put(name, value);
    }

    public String get(String name) {
        if (!headerExists(name))
            throw new HeaderNotFoundException(name);
        return values.get(name);
    }

    public boolean headerExists(String name) {
        return values.containsKey(name);
    }

    public Collection<String> getNames() {
        return values.keySet();
    }

    public int size() {
        return values.size();
    }

    public boolean contains(String headerName) {
        return values.containsKey(headerName);
    }

    public void addBearerToken(String token) {
        Header header = new BearerTokenHeader(token);
        add(header);
    }

    public String getBearerToken() {
        return get(BEARER_TOKEN.get());
    }

    public boolean hasBearerToken() {
        return contains(BEARER_TOKEN.get());
    }

    public void addJwtAssertion(String application) {
        Header header = new JwtAssertionHeader(application);
        add(header);
    }

    public String getJwtAssertion() {
        return get(JWT_ASSERTION.get());
    }

    public boolean hasJwtAssertion() {
        return contains(JWT_ASSERTION.get());
    }

    public void addContentType(String contentType) {
        Header header = new ContentTypeHeader(contentType);
        add(header);
    }

    public String getContentType() {
        return get(CONTENT_TYPE.get());
    }

    public boolean hasContentType() {
        return contains(CONTENT_TYPE.get());
    }

    public void addAccept(String contentType) {
        Header header = new AcceptHeader(contentType);
        add(header);
    }

    public String getAccept() {
        return get(ACCEPT.get());
    }


    public boolean hasAccept() {
        return contains(ACCEPT.get());
    }

    public void addBasicAuth(String token) {
        Header header = new BasicAuthHeader(token);
        add(header);
    }

    public String getBasicAuth() {
        return get(BASIC_AUTH.get());
    }

    public boolean hasBasicAuth() {
        return contains(BASIC_AUTH.get());
    }

    public void addAuthToken(String token) {
        Header header = new AuthTokenHeader(token);
        add(header);
    }

    public String getAuthToken() {
        return get(AUTH_TOKEN.get());
    }

    public boolean hasAuthToken() {
        return contains(AUTH_TOKEN.get());
    }

}
