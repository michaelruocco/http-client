package uk.co.mruoc.http.client;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicAuthTokenGeneratorTest {

    private final BasicAuthTokenGenerator generator = new BasicAuthTokenGenerator();

    @Test
    public void shouldConcatenateKeyColonAndSecretAndBase64Encode() {
        String token = generator.generateToken("my-key", "my-secret");

        assertThat(token).isEqualTo("bXkta2V5Om15LXNlY3JldA==");
    }

    @Test
    public void shouldReturnTokenHeader() {
        Header tokenHeader = generator.generateTokenHeader("my-key", "my-secret");

        assertThat(tokenHeader.getName()).isEqualTo("Authorization");
        assertThat(tokenHeader.getValue()).isEqualTo("Basic bXkta2V5Om15LXNlY3JldA==");
    }

}
