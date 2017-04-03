package uk.co.mruoc.http.client;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class HeadersTest {

    private final Headers headers = new Headers();

    @Test(expected = HeaderNotFoundException.class)
    public void shouldThrowExceptionIfHeaderDoesNotExist() {
        headers.get("invalid");
    }

    @Test
    public void shouldWriteHeaderValues() {
        headers.add("name", "value1");

        assertThat(headers.get("name")).isEqualTo("value1");
    }

    @Test
    public void shouldOverwriteExistingHeaderValues() {
        headers.add("name", "value1");
        headers.add("name", "value2");

        assertThat(headers.get("name")).isEqualTo("value2");
    }

    @Test
    public void shouldReturnHeaderKeys() {
        headers.add("name1", "value1");
        headers.add("name2", "value2");

        assertThat(headers.getNames()).containsExactlyInAnyOrder("name1", "name2");
    }

    @Test
    public void headerExistsShouldReturnFalseIfHeaderDoesNotExist() {
        assertThat(headers.headerExists("name1")).isFalse();
    }

    @Test
    public void headerExistsShouldReturnTrueIfHeaderExists() {
        headers.add("name1", "value1");

        assertThat(headers.headerExists("name1")).isTrue();
    }

}
