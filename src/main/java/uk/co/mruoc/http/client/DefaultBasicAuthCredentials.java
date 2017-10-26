package uk.co.mruoc.http.client;

public class DefaultBasicAuthCredentials implements BasicAuthCredentials {

    private final String key;
    private final String secret;

    public DefaultBasicAuthCredentials(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getSecret() {
        return secret;
    }

}
