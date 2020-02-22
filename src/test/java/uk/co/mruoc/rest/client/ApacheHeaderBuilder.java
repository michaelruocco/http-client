package uk.co.mruoc.rest.client;

public class ApacheHeaderBuilder {

    public static org.apache.http.Header build(String name, String value) {
        return new org.apache.http.message.BasicHeader(name, value);
    }

}
