package uk.co.mruoc.http.client.header;

import org.apache.http.HttpMessage;

import java.util.*;

import static uk.co.mruoc.http.client.header.CommonHeaderName.*;
import static uk.co.mruoc.http.client.header.CommonHeaderName.AUTHORIZATION;

public class DefaultHeaders implements Headers {

    private final Map<String, String> values = new HashMap<>();

    public DefaultHeaders() {
        // intentionally blank
    }

    public DefaultHeaders(HttpMessage message) {
        addHeaders(message);
    }

    @Override
    public void addHeaders(HttpMessage message) {
        addHeaders(message.getAllHeaders());
    }

    @Override
    public void addHeaders(org.apache.http.Header... headers) {
        Arrays.stream(headers).forEach(h -> add(h.getName(), h.getValue()));
    }

    @Override
    public void add(Header header) {
        add(header.getName(), header.getValue());
    }

    @Override
    public void add(String name, String value) {
        values.put(name, value);
    }

    @Override
    public String get(String name) {
        if (!contains(name))
            throw new HeaderNotFoundException(name);
        return values.get(name);
    }

    @Override
    public Collection<String> getNames() {
        return values.keySet();
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public boolean contains(String headerName) {
        return values.containsKey(headerName);
    }

    @Override
    public Map<String, String> values() {
        return Collections.unmodifiableMap(values);
    }

    @Override
    public void addBearerToken(String token) {
        Header header = new BearerTokenHeader(token);
        add(header);
    }

    @Override
    public void addBasicAuth(String token) {
        Header header = new BasicAuthHeader(token);
        add(header);
    }

    @Override
    public String getAuthorization() {
        return get(AUTHORIZATION.get());
    }

    @Override
    public boolean hasAuthorization() {
        return contains(AUTHORIZATION.get());
    }

    @Override
    public void addContentType(String contentType) {
        Header header = new ContentTypeHeader(contentType);
        add(header);
    }

    @Override
    public String getContentType() {
        return get(CONTENT_TYPE.get());
    }

    @Override
    public boolean hasContentType() {
        return contains(CONTENT_TYPE.get());
    }

    @Override
    public void addAccept(String contentType) {
        Header header = new AcceptHeader(contentType);
        add(header);
    }

    @Override
    public String getAccept() {
        return get(ACCEPT.get());
    }

    @Override
    public boolean hasAccept() {
        return contains(ACCEPT.get());
    }

    @Override
    public boolean hasSameValues(Headers headers) {
        return this.values.equals(headers.values());
    }

}
