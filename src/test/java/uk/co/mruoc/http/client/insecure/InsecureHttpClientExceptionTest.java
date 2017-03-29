package uk.co.mruoc.http.client.insecure;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InsecureHttpClientExceptionTest {

    private final Throwable cause = new Exception();

    @Test
    public void shouldReturnCause() {
        Throwable exception = new InsecureHttpClientException(cause);

        assertThat(exception.getCause()).isEqualTo(cause);
    }

}
