package com.thorium.restassured.clickclack.api.tests.draft.v1;

import com.thorium.restassured.commons.TestSet;
import com.thorium.restassured.clickclack.api.models.Clack;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ClacksEndPointTestSet extends TestSet {

    @Test(
            groups = {"ep"},
            description = "GET /clacks => 200"
    )
    public void getClacksHasStatusCodeOk() {
        given()
            .log().body()
        .when()
            .get(getAPI_URI() + "/")
        .then()
            .log().body()
        .and()
            .statusCode(200);
    }

    @Test(
            groups = {"ep"},
            description = "POST /clacks => 201"
    )
    public void postClacksHasStatusCodeCreated() {
        Clack newClack = new Clack();
        Gson gson = new Gson();
        String json = gson.toJson(newClack);
        given()
            .log().body()
        .with()
            .header("Content-Type", "application/json")
            .body(json)
        .when()
            .post(getAPI_URI() + "/clacks/")
        .then()
            .log().body()
        .and()
            .statusCode(201);
    }
}
