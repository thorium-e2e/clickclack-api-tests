package com.apothuaud.testng.listeners;

import org.testng.*;

import static io.restassured.RestAssured.given;

public class SlackNotifier implements ITestListener, ISuiteListener {

    public void onStart(ISuite suite) {
        given()
            .log().body()
        .with()
            .header("Content-Type", "application/json")
            .body("{\"text\": \"Début d'exécution des tests.\n" + suite.getName() + "\"}")
        .when()
            .post("https://hooks.slack.com/services/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")
        .then()
            .log().body()
        .and()
            .statusCode(200);
    }

    public void onFinish(ISuite suite) {
        given()
            .log().body()
        .with()
            .header("Content-Type", "application/json")
            .body("{\"text\": \"Fin d'exécution des tests.\n\" + suite.getName() + \"\"}")
        .when()
            .post("https://hooks.slack.com/services/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")
        .then()
            .log().body()
        .and()
            .statusCode(200);
    }

    public void onTestStart(ITestResult result) {

    }

    public void onTestSuccess(ITestResult result) {

    }

    public void onTestFailure(ITestResult result) {
        given()
            .log().body()
        .with()
            .header("Content-Type", "application/json")
            .body("{\"text\": \"1 Test => FAILED\", \"attachments\":[{\"fallback\":\"fallback\", \"pretext\":\"détails ci-dessous\", \"color\":\"#D00000\", \"fields\":[{\"title\":\"" + result.getMethod().getQualifiedName() + "\", \"value\":\"" + result.getThrowable().getMessage() + "\", \"short\":false}]}]}")
        .when()
            .post("https://hooks.slack.com/services/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")
        .then()
            .log().body()
        .and()
            .statusCode(200);
    }

    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {

    }
}
