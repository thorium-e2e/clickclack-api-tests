package com.clickclack.api.autotests.draft.v1;

import com.clickclack.api.autotests.RestAssuredApiTestSet;
import com.clickclack.api.autotests.common.ExtentLogger;
import com.clickclack.api.autotests.common.MyRequest;
import com.clickclack.api.autotests.common.MyResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RootEndPointTestSet extends RestAssuredApiTestSet {

    @Test(
            groups = {"ep"},
            description = "GET / => 200",
            dataProvider = "ApiUriProvider"
    )
    public void getApiHomeHasStatusCodeOk(String API_URI) {
        MyRequest request = new MyRequest("GET", API_URI + "/", null, null);
        ExtentLogger.INFO(request.toString());
        MyResponse response = request.send();
        ExtentLogger.INFO(String.valueOf(response.getStatusCode()));
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
