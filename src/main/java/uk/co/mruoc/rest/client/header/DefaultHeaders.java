package uk.co.mruoc.rest.client.header;

import org.apache.http.HttpMessage;

import java.util.*;

public class DefaultHeaders implements Headers {

    private final Map<String, String> values = new HashMap<>();

    public DefaultHeaders() {
        // intentionally blank
    }

    public DefaultHeaders(HttpMessage message) {
        setHeaders(message);
    }

    @Override
    public void setHeaders(HttpMessage message) {
        setHeaders(message.getAllHeaders());
    }

    @Override
    public void setHeaders(org.apache.http.Header... headers) {
        Arrays.stream(headers).forEach(h -> set(h.getName(), h.getValue()));
    }

    @Override
    public void set(Header header) {
        set(header.getName(), header.getValue());
    }

    @Override
    public void set(String name, String value) {
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
    public void setBearerToken(String token) {
        Header header = new BearerTokenHeader(token);
        set(header);
    }

    @Override
    public void setBasicAuth(String token) {
        Header header = new BasicAuthHeader(token);
        set(header);
    }

    @Override
    public String getAuthorization() {
        return get(CommonHeaderName.AUTHORIZATION);
    }

    @Override
    public boolean hasAuthorization() {
        return contains(CommonHeaderName.AUTHORIZATION);
    }

    @Override
    public void setContentType(String contentType) {
        Header header = new ContentTypeHeader(contentType);
        set(header);
    }

    @Override
    public String getContentType() {
        return get(CommonHeaderName.CONTENT_TYPE);
    }

    @Override
    public boolean hasContentType() {
        return contains(CommonHeaderName.CONTENT_TYPE);
    }

    @Override
    public void setAccept(String contentType) {
        Header header = new AcceptHeader(contentType);
        set(header);
    }

    @Override
    public String getAccept() {
        return get(CommonHeaderName.ACCEPT);
    }

    @Override
    public boolean hasAccept() {
        return contains(CommonHeaderName.ACCEPT);
    }

    @Override
    public boolean hasSameValues(Headers headers) {
        return this.values.equals(headers.values());
    }

}
