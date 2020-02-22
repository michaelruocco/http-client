package uk.co.mruoc.rest.client.header;

import org.apache.http.HttpMessage;

import java.util.Collection;
import java.util.Map;

public interface Headers {

    void setHeaders(HttpMessage message);

    void setHeaders(org.apache.http.Header... headers);

    void set(Header header);

    void set(String name, String value);

    String get(String name);

    Collection<String> getNames();

    int size();

    boolean contains(String headerName);

    Map<String, String> values();

    void setBearerToken(String token);

    void setBasicAuth(String token);

    String getAuthorization();

    boolean hasAuthorization();

    void setContentType(String contentType);

    String getContentType();

    boolean hasContentType();

    void setAccept(String contentType);

    String getAccept();

    boolean hasAccept();

    boolean hasSameValues(Headers headers);

}
