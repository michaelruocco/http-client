package uk.co.mruoc.http.client;

public class ContentTypeHeader extends BasicHeader {

    public ContentTypeHeader(String contentTypeValue) {
        super(HeaderName.CONTENT_TYPE, contentTypeValue);
    }

}
