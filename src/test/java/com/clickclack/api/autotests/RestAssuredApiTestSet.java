package com.clickclack.api.autotests;

import org.testng.annotations.DataProvider;

public class RestAssuredApiTestSet {

    @DataProvider
    public static Object[][] ApiUriProvider() {
        return new Object[][]{
                {"http://localhost:5000"},
                {"https://rec-clickclack-api.herokuapp.com"},
                {"https://clickclack-api.herokuapp.com"},
        };
    }
}
