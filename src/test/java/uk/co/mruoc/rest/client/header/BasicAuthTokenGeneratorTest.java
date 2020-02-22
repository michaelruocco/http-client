package uk.co.mruoc.rest.client.header;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicAuthTokenGeneratorTest {

    private static final String KEY = "my-key";
    private static final String SECRET = "my-secret";

    private final BasicAuthTokenGenerator generator = new BasicAuthTokenGenerator();

    @Test
    public void shouldGenerateToken() {
        String token = generator.generateToken(KEY, SECRET);

        assertThat(token).isEqualTo("bXkta2V5Om15LXNlY3JldA==");
    }

    @Test
    public void shouldGenerateTokenUsingCredentials() {
        BasicAuthCredentials credentials = new DefaultBasicAuthCredentials(KEY, SECRET);

        String token = generator.generateToken(credentials);

        assertThat(token).isEqualTo("bXkta2V5Om15LXNlY3JldA==");
    }

    @Test
    public void shouldReturnBasicAuthHeader() {
        Header tokenHeader = generator.generateBasicAuthHeader(KEY, SECRET);

        assertThat(tokenHeader.getName()).isEqualTo("Authorization");
        assertThat(tokenHeader.getValue()).isEqualTo("Basic bXkta2V5Om15LXNlY3JldA==");
    }

    @Test
    public void shouldReturnBasicAuthHeaderUsingCredentials() {
        BasicAuthCredentials credentials = new DefaultBasicAuthCredentials(KEY, SECRET);

        Header tokenHeader = generator.generateBasicAuthHeader(credentials);

        assertThat(tokenHeader.getName()).isEqualTo("Authorization");
        assertThat(tokenHeader.getValue()).isEqualTo("Basic bXkta2V5Om15LXNlY3JldA==");
    }

}
