package com.clickclack.api.autotests.draft.v1;

import com.clickclack.api.autotests.RestAssuredApiTestSet;
import com.clickclack.api.autotests.models.Clack;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class ClacksEndPointTestSet extends RestAssuredApiTestSet {

    @Test(
            groups = {"ep"},
            description = "GET /clacks => 200",
            dataProvider = "ApiUriProvider"
    )
    public void getClacksHasStatusCodeOk(String API_URI) {
        given()
            .log().body()
        .when()
            .get(API_URI + "/")
        .then()
            .log().body()
        .and()
            .statusCode(200);
    }

    @Test(
            groups = {"ep"},
            description = "POST /clacks => 201",
            dataProvider = "ApiUriProvider"
    )
    public void postClacksHasStatusCodeCreated(String API_URI) {
        Clack newClack = new Clack();
        Gson gson = new Gson();
        String json = gson.toJson(newClack);
        given()
            .log().body()
        .with()
            .header("Content-Type", "application/json")
            .body(json)
        .when()
            .post(API_URI + "/clacks/")
        .then()
            .log().body()
        .and()
            .statusCode(201);
    }
}
