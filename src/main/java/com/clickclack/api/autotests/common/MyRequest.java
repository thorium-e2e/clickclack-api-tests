package com.clickclack.api.autotests.common;

import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class MyRequest {

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public MyRequest(String method, String URL, HashMap<String, String> headers, String body) {
        this.headers = (headers != null) ? headers : new HashMap<String, String>();
        this.body = (body != null) ? body : "";
        this.method = method;
        this.URL = URL;
    }

    @Override
    public String toString() {
        return    "REQUEST ***********\n"
                + "method:     "
                + this.method.toUpperCase()
                + "\nurl:        "
                + this.URL
                + "\nheaders:\n"
                + this.headers
                + "\nbody:\n"
                + this.body
                + "\n**********";
    }

    HashMap<String, String> headers;
    String body;
    String method;
    String URL;

    public MyResponse send() {

        Response response = given().headers(headers).body(body).when().request(method, this.URL);
        return new MyResponse(response);
    }
}
