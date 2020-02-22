package uk.co.mruoc.rest.client;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ResponseConverterTest {

    private final HttpResponse apacheResponse = mock(HttpResponse.class);

    private final ResponseConverter converter = new ResponseConverter();

    @Test
    public void shouldConvertStatusCode() throws IOException {
        int statusCode = 200;
        givenApacheResponseWillReturnStatusCode(statusCode);
        givenApacheResponseWillReturnNoHeaders();

        Response response = converter.toResponse(apacheResponse);

        assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }

    @Test
    public void shouldConvertHeaders() throws IOException {
        String headerName = "headerName1";
        String headerValue = "headerValue1";
        org.apache.http.Header header = ApacheHeaderBuilder.build(headerName, headerValue);
        givenApacheResponseWillReturnHeaders(header);
        givenApacheResponseWillReturnStatusCode(200);

        Response response = converter.toResponse(apacheResponse);

        assertThat(response.getHeaderKeys()).containsExactly(headerName);
        assertThat(response.getHeader(headerName)).isEqualTo("headerValue1");
    }

    private void givenApacheResponseWillReturnStatusCode(int statusCode) {
        StatusLine statusLine = mock(StatusLine.class);
        given(statusLine.getStatusCode()).willReturn(statusCode);
        given(apacheResponse.getStatusLine()).willReturn(statusLine);
    }

    private void givenApacheResponseWillReturnNoHeaders() {
        givenApacheResponseWillReturnHeaders();
    }

    private void givenApacheResponseWillReturnHeaders(org.apache.http.Header... headers) {
        given(apacheResponse.getAllHeaders()).willReturn(headers);
    }

}
