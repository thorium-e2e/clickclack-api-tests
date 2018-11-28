package com.thorium.restassured.clickclack.api.tests.draft.v1;

import com.thorium.restassured.commons.TestSet;
import com.thorium.restassured.clickclack.api.models.Clack;
import com.google.gson.Gson;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ClacksWithIdEndPointTestSet extends TestSet {

    private String ID;

    @BeforeClass
    public void beforeClass() {
        ArrayList<String> ids =
            when()
                .get(getAPI_URI() + "/clacks")
            .then()
                .statusCode(200)
            .extract()
                .path("_id");
        ID = ids.get(0);
    }

    // get
    @Test(
            groups = {"ep"},
            description = "GET /clacks/ID => 200"
    )
    public void getClacksWithIdHasStatusCodeOk() {
        given()
            .log().body()
        .when()
            .get(getAPI_URI() + "/clacks/" + ID)
        .then()
            .log().body()
        .and()
            .statusCode(200);
    }

    // put
    @Test(
            groups = {"ep"},
            description = "PUT /clacks/ID => 200"
    )
    public void putClacksWithIdHasStatusCodeOk() {
        Clack newClack = new Clack();
        Gson gson = new Gson();
        String json = gson.toJson(newClack);
        given()
            .log().body()
        .with()
            .header("Content-Type", "application/json")
            .body(json)
        .when()
            .put(getAPI_URI() + "/clacks/" + ID)
        .then()
            .log().body()
        .and()
            .statusCode(200);
    }

    // delete
    @Test(
            groups = {"ep"},
            description = "DELETE /clacks/ID => 204"
    )
    public void deleteClacksWithIdHasStatusCodeNoContent() {
        given()
            .log().body()
        .when()
            .delete(getAPI_URI() + "/clacks/" + ID)
        .then()
            .log().body()
        .and()
            .statusCode(204);
    }
}
