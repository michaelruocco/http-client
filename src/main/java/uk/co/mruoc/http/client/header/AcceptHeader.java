package uk.co.mruoc.http.client.header;

public class AcceptHeader extends SimpleHeader {

    public AcceptHeader(String contentType) {
        super(CommonHeaderName.ACCEPT, contentType);
    }

}
