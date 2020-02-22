package uk.co.mruoc.rest.client;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Base64EncoderTest {

    private final Base64Encoder encoder = new Base64Encoder();

    @Test
    public void shouldBase64EncodeValue() {
        String input = "this is my test string";

        String result = encoder.encode(input);

        assertThat(result).isEqualTo("dGhpcyBpcyBteSB0ZXN0IHN0cmluZw==");
    }

}
