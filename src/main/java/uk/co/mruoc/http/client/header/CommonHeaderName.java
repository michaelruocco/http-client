package uk.co.mruoc.http.client.header;

import org.apache.http.HttpHeaders;

public enum CommonHeaderName {

    AUTHORIZATION(HttpHeaders.AUTHORIZATION),
    ACCEPT(HttpHeaders.ACCEPT),
    CONTENT_TYPE(HttpHeaders.CONTENT_TYPE);

    private String value;

    CommonHeaderName(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

}
