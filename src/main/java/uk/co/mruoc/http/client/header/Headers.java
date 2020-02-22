package uk.co.mruoc.http.client.header;

import org.apache.http.HttpMessage;

import java.util.Collection;
import java.util.Map;

public interface Headers {

    void addHeaders(HttpMessage message);

    void addHeaders(org.apache.http.Header... headers);

    void add(Header header);

    void add(String name, String value);

    String get(String name);

    Collection<String> getNames();

    int size();

    boolean contains(String headerName);

    Map<String, String> values();

    void addBearerToken(String token);

    void addBasicAuth(String token);

    String getAuthorization();

    boolean hasAuthorization();

    void addContentType(String contentType);

    String getContentType();

    boolean hasContentType();

    void addAccept(String contentType);

    String getAccept();

    boolean hasAccept();

    boolean hasSameValues(Headers headers);

}
