package uk.co.mruoc.http.client;

public class HeaderNotFoundException extends HttpClientException {

    public HeaderNotFoundException(String headerName) {
        super(headerName);
    }

}
