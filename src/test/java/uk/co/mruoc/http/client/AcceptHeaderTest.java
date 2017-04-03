package uk.co.mruoc.http.client;

import org.apache.http.HttpHeaders;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptHeaderTest {

    private static final String APPLICATION_JSON = "application/json";

    private final Header header = new AcceptHeader(APPLICATION_JSON);

    @Test
    public void shouldReturnNameAsAccept() {
        assertThat(header.getName()).isEqualTo(HttpHeaders.ACCEPT);
    }

    @Test
    public void shouldReturnValueAsContentTypePassed() {
        assertThat(header.getValue()).isEqualTo(APPLICATION_JSON);
    }

}
