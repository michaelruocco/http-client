package uk.co.mruoc.http.client;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BodyExtractorTest {

    private static final String DEFAULT_ENCODING = "utf-8";

    private final BodyExtractor extractor = new BodyExtractor();

    @Test
    public void shouldReturnPostBody() {
        String expectedBody = "post-message-body";
        HttpPost post = new HttpPost();
        post.setEntity(new StringEntity(expectedBody, DEFAULT_ENCODING));

        String result = extractor.extract(post);

        assertThat(result).isEqualTo(expectedBody);
    }

    @Test
    public void shouldReturnPutBody() {
        String expectedBody = "put-message-body";
        HttpPut put = new HttpPut();
        put.setEntity(new StringEntity(expectedBody, DEFAULT_ENCODING));

        String result = extractor.extract(put);

        assertThat(result).isEqualTo(expectedBody);
    }

    @Test
    public void shouldReturnEmptyStringForGetBody() {
        HttpGet get = new HttpGet();

        String result = extractor.extract(get);

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnEmptyStringForDeleteBody() {
        HttpDelete delete = new HttpDelete();

        String result = extractor.extract(delete);

        assertThat(result).isEmpty();
    }

}
