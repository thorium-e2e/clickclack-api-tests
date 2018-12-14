package com.clickclack.api.autotests.common;

import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class MyRequest {

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

    private HashMap<String, String> headers;
    private String body;
    private String method;
    private String URL;

    public MyResponse send() {

        Response response = given().headers(headers).body(body).when().request(method, this.URL);
        return new MyResponse(response);
    }
}
