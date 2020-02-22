package uk.co.mruoc.http.client.header;

import uk.co.mruoc.http.client.Base64Encoder;

public class BasicAuthTokenGenerator {

    private final Base64Encoder base64Encoder = new Base64Encoder();

    public String generateToken(BasicAuthCredentials credentials) {
        return generateToken(credentials.getKey(), credentials.getSecret());
    }

    public String generateToken(String key, String secret) {
        String keyAndSecret = key + ":" + secret;
        return base64Encoder.encode(keyAndSecret);
    }

    public Header generateBasicAuthHeader(BasicAuthCredentials credentials) {
        return generateBasicAuthHeader(credentials.getKey(), credentials.getSecret());
    }

    public Header generateBasicAuthHeader(String key, String secret) {
        String token = generateToken(key, secret);
        return new BasicAuthHeader(token);
    }

}
