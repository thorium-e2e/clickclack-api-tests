package com.clickclack.api.autotests.draft.v1;

import com.clickclack.api.autotests.RestAssuredApiTestSet;
import com.clickclack.api.autotests.models.Clack;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
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
                        .statusCode(200)
                        .extract()
                        .path("_id");
        ID = ids.get(0);
        given()
            .log().body()
        .when()
            .get(API_URI + "/clacks/" + ID)
        .then()
            .log().body()
        .and()
            .statusCode(200);
    }

    @Test(
            groups = {"ep"},
            description = "PUT /clacks/ID => 200",
            dependsOnMethods = {"getClacksWithIdHasStatusCodeOk"},
            dataProvider = "ApiUriProvider"
    )
    public void putClacksWithIdHasStatusCodeOk(String API_URI) {
        Clack newClack = new Clack();
        Gson gson = new Gson();
        String json = gson.toJson(newClack);
        given()
            .log().body()
        .with()
            .header("Content-Type", "application/json")
            .body(json)
        .when()
            .put(API_URI + "/clacks/" + ID)
        .then()
            .log().body()
        .and()
            .statusCode(200);
    }

    // delete
    @Test(
            groups = {"ep"},
            description = "DELETE /clacks/ID => 204",
            dependsOnMethods = {"getClacksWithIdHasStatusCodeOk"},
            dataProvider = "ApiUriProvider"
    )
    public void deleteClacksWithIdHasStatusCodeNoContent(String API_URI) {
        given()
            .log().body()
        .when()
            .delete(API_URI + "/clacks/" + ID)
        .then()
            .log().body()
        .and()
            .statusCode(204);
    }
}
