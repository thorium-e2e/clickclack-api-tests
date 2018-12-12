package com.clickclack.api.autotests.draft.v1;

import com.clickclack.api.autotests.RestAssuredApiTestSet;
import com.clickclack.api.autotests.common.ExtentLogger;
import com.clickclack.api.autotests.common.MyRequest;
import com.clickclack.api.autotests.common.MyResponse;
import com.clickclack.api.autotests.models.Clack;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.when;

public class ClacksWithIdEndPointTestSet extends RestAssuredApiTestSet {

    private static String ID;

    @Test(
            groups = {"ep"},
            description = "GET /clacks/ID => 200",
            dataProvider = "ApiUriProvider"
    )
    public void getClacksWithIdHasStatusCodeOk(String API_URI) {
        ArrayList<String> ids =
            when()
                .get(API_URI + "/clacks")
            .then()
                .extract()
                    .path("_id");
        ID = ids.get(0);
        MyRequest request = new MyRequest("GET", API_URI + "/clacks/" + ID, null, null);
        ExtentLogger.INFO(request.toString());
        MyResponse response = request.send();
        ExtentLogger.INFO(response.toString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(
            groups = {"ep"},
            description = "PUT /clacks/ID => 200",
            dependsOnMethods = {"getClacksWithIdHasStatusCodeOk"},
            dataProvider = "ApiUriProvider"
    )
    public void putClacksWithIdHasStatusCodeOk(String API_URI) {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        Clack newClack = new Clack();
        Gson gson = new Gson();
        String json = gson.toJson(newClack);

        MyRequest request = new MyRequest("PUT", API_URI + "/clacks/" + ID, headers, json);
        ExtentLogger.INFO(request.toString());
        MyResponse response = request.send();
        ExtentLogger.INFO(response.toString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(
            groups = {"ep"},
            description = "DELETE /clacks/ID => 204",
            dependsOnMethods = {"getClacksWithIdHasStatusCodeOk"},
            dataProvider = "ApiUriProvider"
    )
    public void deleteClacksWithIdHasStatusCodeNoContent(String API_URI) {
        MyRequest request = new MyRequest("DELETE", API_URI + "/clacks/" + ID, null, null);
        ExtentLogger.INFO(request.toString());
        MyResponse response = request.send();
        ExtentLogger.INFO(response.toString());
        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
