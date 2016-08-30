# Http Client

[![Build Status](https://travis-ci.org/michaelruocco/http-client.svg?branch=master)](https://travis-ci.org/michaelruocco/http-client)
[![Coverage Status](https://coveralls.io/repos/github/michaelruocco/http-client/badge.svg?branch=master)](https://coveralls.io/github/michaelruocco/http-client?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.michaelruocco/http-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.michaelruocco/http-client)

This project aims to provide a simpler interface for RESTful JSON api calls.
It also includes a fake implementation to make testing of client code easier.

## Usage

To use the library from a program you will need to add a dependency to your project. In
gradle you would do this by adding the following to your build.gradle file:

```
dependencies {
    classpath 'com.github.michaelruocco:http-client:1.0.0'
}
```

You can then create and instance of the SimpleHttpClient class to perform
RESTful api calls e.g.

```
HttpClient client = new SimpleHttpClient();

Response getResponse = client.get("http://localhost:8080/testEndpoint");

Response postResponse = client.post("http://localhost:8080/testEndpoint", "{ json: body }");

Response putResponse = client.put("http://localhost:8080/testEndpoint", "{ json: body }");

Response deleteResponse = client.delete("http://localhost:8080/testEndpoint");
```

For each of the calls you can also pass headers if you wish, e.g:

```
HttpClient client = new SimpleHttpClient();
Headers headers = new Headers();
headers.add("Custom-Header", "Value");

Response response = client.get("http://localhost:8080/testEndpoint", headers);
```

### Testing

When testing you can make use of the FakeGttpClient, this allows you to set
up canned responses as well as inspect any requests set into it. To test a
simple PUT request we can use the fake client to assert on the request body:

```
public class Example {
    private final HttpClient client;

    public Example(HttpClient client) {
        this.client = client;
    }

    public void doPut(String jsonContent) {
        client.put("http://www.example.com", jsonContent);
    }
}

public class ExampleTest {

    @Test
    public void doesPut() throws IOException {
        FakeHttpClient client = new FakeHttpClient();
        Example example = new Example(client);

        String jsonContent = "{\"foo\": \"bar\"}";
        example.doPut(jsonContent);

        assertEquals(jsonContent, client.lastRequestBody());
    }
}
```

## Running the Tests

You can run the tests for this project by running the following command:

```
gradlew clean build
```