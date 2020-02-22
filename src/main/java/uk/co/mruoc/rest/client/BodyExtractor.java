package uk.co.mruoc.rest.client;

import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UncheckedIOException;

public class BodyExtractor {

    public String extract(HttpRequest request) {
        if (request instanceof HttpEntityEnclosingRequestBase) {
            return extract((HttpEntityEnclosingRequestBase) request);
        }
        return "";
    }

    private String extract(HttpEntityEnclosingRequestBase request) {
        try {
            return EntityUtils.toString(request.getEntity());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
