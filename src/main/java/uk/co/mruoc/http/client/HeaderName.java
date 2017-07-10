package uk.co.mruoc.http.client;

import org.apache.http.HttpHeaders;

public enum HeaderName {

    AUTH_TOKEN("X-Auth-Token"),
    JWT_ASSERTION("X-JWT-Assertion"),
    BEARER_TOKEN(HttpHeaders.AUTHORIZATION),
    BASIC_AUTH(HttpHeaders.AUTHORIZATION),
    ACCEPT(HttpHeaders.ACCEPT),
    CONTENT_TYPE(HttpHeaders.CONTENT_TYPE);

    private String value;

    HeaderName(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

}
