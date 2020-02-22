package uk.co.mruoc.http.client.header;

public class ContentTypeHeader extends SimpleHeader {

    public ContentTypeHeader(String contentTypeValue) {
        super(CommonHeaderName.CONTENT_TYPE, contentTypeValue);
    }

}
