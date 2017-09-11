# Http Client

[![Build Status](https://travis-ci.org/michaelruocco/http-client.svg?branch=master)](https://travis-ci.org/michaelruocco/http-client)
[![Coverage Status](https://coveralls.io/repos/github/michaelruocco/http-client/badge.svg?branch=master)](https://coveralls.io/github/michaelruocco/http-client?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/54ce00d4a4084dabba53f5e2c5ef9a01)](https://www.codacy.com/app/michael-ruocco/http-client?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/http-client&amp;utm_campaign=Badge_Grade)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.michaelruocco/http-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.michaelruocco/http-client)

This project aims to provide a simpler interface for RESTful JSON api calls.
It also includes a fake implementation to make testing of client code easier.

## Usage

To use the library from a program you will need to add a dependency to your project. In
gradle you would do this by adding the following to your build.gradle file:

```
dependencies {
    compile 'com.github.michaelruocco:http-client:4.0.1'
}
```

### Basic Usage

You can then create and instance of the SimpleHttpClient class to perform
RESTful api calls e.g.

```
HttpClient client = new SimpleHttpClient();

Response getResponse = client.get("http://localhost:8080/testEndpoint");

Response postResponse = client.post("http://localhost:8080/testEndpoint", "{ json: body }");

Response putResponse = client.put("http://localhost:8080/testEndpoint", "{ json: body }");

Response deleteResponse = client.delete("http://localhost:8080/testEndpoint");
```

### Setting Headers

For each of the calls you can also pass headers if you wish, e.g:

```
HttpClient client = new SimpleHttpClient();
Headers headers = new Headers();
headers.add("Custom-Header", "Value");

Response response = client.get("http://localhost:8080/testEndpoint", headers);
```

If you would rather encapsulate the header name values you can also use some of the
default header implementations for common headers such as authorization bearer token
and content type:

```
HttpClient client = new SimpleHttpClient();
Headers headers = new Headers();
headers.add(new BearerTokenHeader("my-token-value"));
headers.add(new ContentTypeHeader("application/json"));

Response response = client.get("http://localhost:8080/testEndpoint", headers);
```

These examples all extend BasicHeader, if you want to add others you can extend
BasicHeader to create your own header types. If you would rather not maintain these
yourself then you can create a pull request and commit them back into the project.

### Bypassing / Disabling SSL

For testing purposes it is sometimes preferable to diasble SSL when making http calls
if you don't want to have to package up a truststore with projects. To do this you
can create an instance of InsecureSimpleHttpClient. It is only recommended that you do
this when testing, and never in a production system.

```
HttpClient client = new InsecureSimpleHttpClient();

...
```

## Testing

When testing you can make use of the FakeHttpClient, this allows you to set
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

## Checking dependencies

You can check the current dependencies used by the project to see whether
or not they are currently up to date by running the following command:

```
gradlew dependencyUpdates
```