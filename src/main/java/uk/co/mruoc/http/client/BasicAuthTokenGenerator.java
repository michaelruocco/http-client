package uk.co.mruoc.http.client;

public class BasicAuthTokenGenerator {

    private final Base64Encoder base64Encoder = new Base64Encoder();

    public String generateToken(String key, String secret) {
        String keyAndSecret = key + ":" + secret;
        return base64Encoder.encode(keyAndSecret);
    }

    public Header generateTokenHeader(String key, String secret) {
        String token = generateToken(key, secret);
        return new BasicAuthHeader(token);
    }

}
