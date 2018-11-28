package com.thorium.restassured.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pmw.tinylog.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


@SuppressWarnings("unused")
public class TestSet {

    private static HashMap env;

    private static String API_URI;

    private static HashMap getEnv() {
        return env;
    }

    private static void setEnv(HashMap env) {
        TestSet.env = env;
    }

    public String getAPI_URI() {
        return API_URI;
    }

    private static void setAPI_URI(String uri) {
        API_URI = uri;
    }

    @BeforeSuite
    public static void setUp() throws IOException {
        Logger.info("Setting up test suite for Rest Assured...");
        String JSON_SOURCE_PATH = "src/test/resources/config/env.json";
        File JSON_SOURCE = new File(JSON_SOURCE_PATH);
        setEnv(new ObjectMapper().readValue(JSON_SOURCE, HashMap.class));
        setAPI_URI(getEnv().get("api_uri").toString());
    }

    @BeforeMethod
    public static void beforeMethod() {
        Logger.info("--- NEW TEST METHOD ---");
    }
}
