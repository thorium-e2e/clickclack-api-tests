package com.clickclack.api.autotests;

import org.testng.annotations.DataProvider;

public class RestAssuredApiTestSet {

    @DataProvider
    public static Object[][] ApiUriProvider() {
        return new Object[][]{
                {"https://rec-clickclack-api.herokuapp.com"}
        };
    }
}
