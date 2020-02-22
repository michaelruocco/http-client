package uk.co.mruoc.rest.client.header;

public class AcceptHeader extends SimpleHeader {

    public AcceptHeader(String contentType) {
        super(CommonHeaderName.ACCEPT, contentType);
    }

}
