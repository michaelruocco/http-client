package uk.co.mruoc.rest.client;

import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UncheckedIOException;

public class BodyExtractor {

    public String extract(HttpRequest request) {
        try {
            if (request instanceof HttpPost)
                return extract((HttpPost) request);

            if (request instanceof HttpPut)
                return extract((HttpPut) request);

            return ""; // return body for get or delete, they shouldn't have a body
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String extract(HttpPost post) throws IOException {
        return EntityUtils.toString(post.getEntity());
    }

    private String extract(HttpPut put) throws IOException {
        return EntityUtils.toString(put.getEntity());
    }

}
