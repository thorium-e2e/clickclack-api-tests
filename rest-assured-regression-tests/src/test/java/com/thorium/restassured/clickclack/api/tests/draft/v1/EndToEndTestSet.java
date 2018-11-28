package com.thorium.restassured.clickclack.api.tests.draft.v1;

import com.thorium.restassured.commons.TestSet;
import com.thorium.restassured.clickclack.api.models.Clack;
import com.google.gson.Gson;
import org.pmw.tinylog.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class EndToEndTestSet extends TestSet {

    @Test(
            groups = {"e2e"},
            description = "CRUD Clacks"
    )
    public void CRUDClacks() {
        Logger.info("Open API URI and verify status code...");
        given()
            .log().body()
        .when()
            .get(getAPI_URI() + "/")
        .then()
            .log().body()
        .and()
            .statusCode(200);

        Logger.info("Open clacks endpoint, verify status code and extract an id...");
        ArrayList<String> ids =
            given()
                .log().body()
            .when()
                .get(getAPI_URI() + "/clacks")
            .then()
                .log().body()
            .and()
                .statusCode(200)
            .extract()
                .path("_id");
        int nbClacksBefore = ids.size();
        String testClackId = ids.get(0);
        System.out.println("Test Clack id found: " + testClackId);

        Logger.info("Open clack endpoint by id and verify status code...");
        given()
            .log().body()
        .when()
            .get(getAPI_URI() + "/clacks/" + testClackId)
        .then()
            .log().body()
        .and()
            .statusCode(200);

        Logger.info("Create a new Clack and verify status code...");
        Clack newClack = new Clack();
        newClack.setAuthor("Rest Assured");
        newClack.setPurpose("Tests auto");
        newClack.setTitle("test Clack");
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

        Logger.info("Open clacks endpoint, verify status code and clacks nb...");
        ids =
                given()
                    .log().body()
                .when()
                    .get(getAPI_URI() + "/clacks")
                .then()
                    .log().body()
                .and()
                    .statusCode(200)
                .extract()
                    .path("_id");
        int nbClacks = ids.size();
        Assert.assertTrue(nbClacks > nbClacksBefore);

        Logger.info("Create a new Clack and verify status code...");
        newClack = new Clack();
        newClack.setTitle("UPDATED");
        newClack.setDescription("Updated by rest assured auto tests");
        gson = new Gson();
        json = gson.toJson(newClack);
        given()
                .log().body()
                .with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .put(getAPI_URI() + "/clacks/" + testClackId)
                .then()
                .log().body()
                .and()
                .statusCode(200);

        Logger.info("Open clack endpoint by id and verify status code...");
        given()
                .log().body()
                .when()
                .get(getAPI_URI() + "/clacks/" + testClackId)
                .then()
                .log().body()
                .and()
                .statusCode(200)
                .extract()
                .path("title");
        String desc =
            given()
                .log().body()
            .when()
                .get(getAPI_URI() + "/clacks/" + testClackId)
            .then()
                .log().body()
            .and()
                .statusCode(200)
            .extract()
                .path("description");
        Assert.assertNotNull(desc);

        Logger.info("Delete clack endpoint by id and verify status code...");
        given()
            .log().body()
        .when()
            .delete(getAPI_URI() + "/clacks/" + testClackId)
        .then()
            .log().body()
        .and()
            .statusCode(204);

        Logger.info("Open clacks endpoint, verify status code and clacks nb...");
        ids =
            given()
                .log().body()
            .when()
                .get(getAPI_URI() + "/clacks")
            .then()
                .log().body()
            .and()
                .statusCode(200)
            .extract()
                .path("_id");
        nbClacks = ids.size();
        Assert.assertEquals(nbClacks, nbClacksBefore);
    }
}
