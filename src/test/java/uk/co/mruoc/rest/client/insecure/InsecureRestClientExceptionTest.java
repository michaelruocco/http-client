package uk.co.mruoc.rest.client.insecure;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InsecureRestClientExceptionTest {

    private final Throwable cause = new Exception();

    @Test
    public void shouldReturnCause() {
        Throwable exception = new InsecureHttpClientException(cause);

        assertThat(exception.getCause()).isEqualTo(cause);
    }

}
