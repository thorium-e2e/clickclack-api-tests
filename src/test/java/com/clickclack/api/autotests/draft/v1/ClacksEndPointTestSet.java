package com.clickclack.api.autotests.draft.v1;

import com.clickclack.api.autotests.RestAssuredApiTestSet;
import com.clickclack.api.autotests.common.ExtentLogger;
import com.clickclack.api.autotests.common.MyRequest;
import com.clickclack.api.autotests.common.MyResponse;
import com.clickclack.api.autotests.models.Clack;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ClacksEndPointTestSet extends RestAssuredApiTestSet {

    @Test(
            groups = {"ep"},
            description = "GET /clacks => 200",
            dataProvider = "ParametrizedProvider"
    )
    public void getClacksHasStatusCodeOk(String API_URI) {
        MyRequest request = new MyRequest("GET", API_URI + "/clacks", null, null);
        ExtentLogger.INFO(request.toString());
        MyResponse response = request.send();
        ExtentLogger.INFO(response.toString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(
            groups = {"ep"},
            description = "POST /clacks => 201",
            dataProvider = "ParametrizedProvider"
    )
    public void postClacksHasStatusCodeCreated(String API_URI) {

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        Clack newClack = new Clack();
        Gson gson = new Gson();
        String json = gson.toJson(newClack);

        MyRequest request = new MyRequest("POST", API_URI + "/clacks", headers, json);
        ExtentLogger.INFO(request.toString());
        MyResponse response = request.send();
        ExtentLogger.INFO(response.toString());
        Assert.assertEquals(response.getStatusCode(), 201);
    }
}
