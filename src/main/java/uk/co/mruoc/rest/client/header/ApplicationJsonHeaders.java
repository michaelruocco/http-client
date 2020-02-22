package uk.co.mruoc.rest.client.header;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class ApplicationJsonHeaders extends DefaultHeaders {

    public ApplicationJsonHeaders() {
        set(new ContentTypeHeader(APPLICATION_JSON.getMimeType()));
        set(new AcceptHeader(APPLICATION_JSON.getMimeType()));
    }

}
