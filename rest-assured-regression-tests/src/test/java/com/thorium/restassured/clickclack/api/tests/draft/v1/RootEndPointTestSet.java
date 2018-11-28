package com.thorium.restassured.clickclack.api.tests.draft.v1;

import com.thorium.restassured.commons.TestSet;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RootEndPointTestSet extends TestSet {

    @Test(
            groups = {"ep"},
            description = "GET / => 200"
    )
    public void getApiHomeHasStatusCodeOk() {
        given()
            .log().body()
        .when()
            .get(getAPI_URI() + "/")
        .then()
            .log().body()
        .and()
            .statusCode(200);
    }
}
