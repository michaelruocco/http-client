package uk.co.mruoc.rest.client.header;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class HeaderFormatter {

    private HeaderFormatter() {
        //utility class
    }

    public static String toString(Headers headers) {
        return toString(headers.values()
                .entrySet()
                .stream()
                .map(HeaderFormatter::toString)
                .collect(Collectors.toList()));
    }

    public static String toString(org.apache.http.Header[] headers) {
        return toString(Arrays.stream(headers)
                .map(HeaderFormatter::toString)
                .collect(Collectors.toList()));
    }

    private static String toString(final Header header) {
        return toString(header.getName(), header.getValue());
    }

    private static String toString(final Map.Entry<String, String> header) {
        return toString(header.getKey(), header.getValue());
    }

    private static String toString(final String name, final String value) {
        return String.format("{%s:%s}", name, value);
    }

    private static String toString(Collection<String> values) {
        return String.format("{%s}", StringUtils.join(values, ","));
    }

}
