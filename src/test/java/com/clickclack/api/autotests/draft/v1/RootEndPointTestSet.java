package com.clickclack.api.autotests.draft.v1;

import com.clickclack.api.autotests.RestAssuredApiTestSet;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RootEndPointTestSet extends RestAssuredApiTestSet {

    @Test(
            groups = {"ep"},
            description = "GET / => 200",
            dataProvider = "ApiUriProvider"
    )
    public void getApiHomeHasStatusCodeOk(String API_URI) {
        given()
            .log().body()
        .when()
            .get(API_URI + "/")
        .then()
            .log().body()
        .and()
            .statusCode(200);
    }
}
