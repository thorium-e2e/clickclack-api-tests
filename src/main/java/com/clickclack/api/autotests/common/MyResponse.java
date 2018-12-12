package com.clickclack.api.autotests.common;

import io.restassured.response.Response;

public class MyResponse {

    Response response;

    public MyResponse(Response response) {
        this.response = response;
    }

    public int getStatusCode() {
        return this.response.statusCode();
    }

    @Override
    public String toString() {
        return    "RESPONSE ***********\n"
                + "\nbody:\n"
                + this.response.getBody().prettyPrint()
                + "\n**********";
    }
}
