package uk.co.mruoc.http.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import uk.co.mruoc.log.Logger;
import uk.co.mruoc.log.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import static java.net.URLDecoder.decode;

public class JsonLogSimpleHttpClient extends AbstractSimpleHttpClient {

    private static final String DEFAULT_ENCODING = "utf-8";
    private static final Logger LOG = LoggerFactory.getLogger(JsonLogSimpleHttpClient.class);

    private final BodyExtractor bodyExtractor = new BodyExtractor();

    public JsonLogSimpleHttpClient() {
        this(ApacheHttpClientFactory.build());
    }

    public JsonLogSimpleHttpClient(int httpTimeout) {
        this(ApacheHttpClientFactory.build(httpTimeout));
    }

    public JsonLogSimpleHttpClient(HttpClient client) {
        super(client);
    }

    @Override
    protected void log(HttpRequestBase request) {
        try {
            URI uri = request.getURI();
            LOG.info().message("making request")
                    .field("method", request.getMethod())
                    .field("decodedUri", decode(uri.toString(), DEFAULT_ENCODING))
                    .field("encodedUri", uri.toString())
                    .list("headers", new RequestHeaderSupplier(request))
                    .json("body", new RequestBodySupplier(request))
                    .log();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    protected void log(Response response) {
        LOG.info().message("got response")
                .field("statusCode", response.getStatusCode())
                .json("body", new ResponseBodySupplier(response))
                .field("headers", new ResponseHeaderSupplier(response))
                .log();
    }

    private static class ResponseHeaderSupplier implements Supplier<List> {

        private final Response response;

        public ResponseHeaderSupplier(Response response) {
            this.response = response;
        }

        @Override
        public List get() {
            Collection<String> headerKeys = response.getHeaderKeys();
            List<String> values = new ArrayList<>();
            headerKeys.forEach(key -> values.add("name: " + key + ", value: " + response.getHeader(key)));
            return values;
        }
    }

    private static class ResponseBodySupplier implements Supplier<JsonElement> {

        private final JsonParser parser = new JsonParser();
        private final Response response;

        public ResponseBodySupplier(Response response) {
            this.response = response;
        }

        @Override
        public JsonElement get() {
            return parser.parse(response.getBody());
        }

    }

    private static class RequestHeaderSupplier implements Supplier<List> {

        private final Collection<org.apache.http.Header> headers;

        public RequestHeaderSupplier(HttpRequest request) {
            this.headers = Arrays.asList(request.getAllHeaders());
        }

        @Override
        public List get() {
            List<String> values = new ArrayList<>();
            headers.forEach(header -> values.add("name: " + header.getName() + ", value: " + header.getValue()));
            return values;
        }

    }

    private static class RequestBodySupplier implements Supplier<JsonElement> {

        private final BodyExtractor bodyExtractor = new BodyExtractor();
        private final JsonParser parser = new JsonParser();
        private final HttpRequest request;

        public RequestBodySupplier(HttpRequest request) {
            this.request = request;
        }

        @Override
        public JsonElement get() {
            String body = bodyExtractor.extract(request);
            return parser.parse(body);
        }

    }

}
