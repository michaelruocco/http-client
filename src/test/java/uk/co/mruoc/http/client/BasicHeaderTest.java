package uk.co.mruoc.http.client;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicHeaderTest {

    private static final String NAME = "header-name";
    private static final String VALUE = "header-value";

    private final Header header = new BasicHeader(NAME, VALUE);

    @Test
    public void shouldReturnName() {
        assertThat(header.getName()).isEqualTo(NAME);
    }

    @Test
    public void shouldReturnValue() {
        assertThat(header.getValue()).isEqualTo(VALUE);
    }
    
}
