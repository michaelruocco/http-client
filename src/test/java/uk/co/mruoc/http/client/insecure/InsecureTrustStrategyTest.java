package uk.co.mruoc.http.client.insecure;

import org.apache.http.ssl.TrustStrategy;
import org.junit.Test;
import uk.co.mruoc.http.client.insecure.InsecureTrustStrategy;

import java.security.cert.CertificateException;

import static org.assertj.core.api.Assertions.assertThat;

public class InsecureTrustStrategyTest {

    private final TrustStrategy strategy = new InsecureTrustStrategy();

    @Test
    public void shouldAlwaysBeTrusted() throws CertificateException {
        assertThat(strategy.isTrusted(null, null)).isTrue();
    }

}
