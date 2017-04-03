package uk.co.mruoc.http.client;

import org.apache.http.HttpHeaders;

public class ContentTypeHeader extends BasicHeader {

    public ContentTypeHeader(String contentTypeValue) {
        super(HttpHeaders.CONTENT_TYPE, contentTypeValue);
    }

}
