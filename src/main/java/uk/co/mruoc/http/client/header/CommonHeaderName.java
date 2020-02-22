package uk.co.mruoc.http.client.header;

import org.apache.http.HttpHeaders;

public class CommonHeaderName {

    public static final String AUTHORIZATION = HttpHeaders.AUTHORIZATION;
    public static final String ACCEPT = HttpHeaders.ACCEPT;
    public static final String CONTENT_TYPE = HttpHeaders.CONTENT_TYPE;

    private CommonHeaderName() {
        // utility class
    }

}
