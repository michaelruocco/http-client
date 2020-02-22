package uk.co.mruoc.rest.client.test;

import uk.co.mruoc.rest.client.Method;
import uk.co.mruoc.rest.client.Request;
import uk.co.mruoc.rest.client.Response;
import uk.co.mruoc.rest.client.RestClient;
import uk.co.mruoc.rest.client.header.Headers;

import java.util.List;

public interface FakeRestClient extends RestClient {

    List<Request> allRequests();

    Method lastRequestMethod();

    String lastRequestBody();

    String lastRequestHeader(String name);

    Headers lastRequestHeaders();

    Request lastRequest();

    String lastRequestUri();

    void cannedResponse(int status);

    void cannedResponse(int status, String entity);

    void cannedResponse(int status, String entity, Headers headers);

    void cannedResponse(Response response);

    void throwsException(RuntimeException exception);

    void throwsIoException();

}
