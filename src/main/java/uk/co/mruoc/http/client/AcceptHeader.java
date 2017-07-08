package uk.co.mruoc.http.client;

public class AcceptHeader extends BasicHeader {

    public AcceptHeader(String contentType) {
        super(HeaderName.ACCEPT, contentType);
    }

}
