package com.clickclack.api.autotests.common;

import io.restassured.response.Response;

public class MyResponse {

    private Response response;

    MyResponse(Response response) {
        this.response = response;
    }

    public int getStatusCode() {
        return this.response.statusCode();
    }

    @Override
    public String toString() {
        return    "RESPONSE ***********\n"
                + "- SC : " + String.valueOf(getStatusCode()) + "\n"
                + "- body:\n"
                + this.response.getBody().prettyPrint()
                + "\n**********";
    }
}
