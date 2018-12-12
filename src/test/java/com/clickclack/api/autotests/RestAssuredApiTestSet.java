package com.clickclack.api.autotests;

import org.testng.annotations.DataProvider;

public class RestAssuredApiTestSet {

    @DataProvider
    public static Object[][] ParametrizedProvider() throws Exception {
        if(System.getProperty("data.provider") == null){
            throw new Exception("Pas de data provider!");
        } else if (System.getProperty("data.provider") == "local"){
            return new Object[][]{
                    {"http://localhost:5000"},
            };
        }else if (System.getProperty("data.provider") == "rec"){
            return new Object[][]{
                    {"https://rec-clickclack-api.herokuapp.com"},
            };
        }else if (System.getProperty("data.provider") == "prod"){
            return new Object[][]{
                    {"https://clickclack-api.herokuapp.com"},
            };
        }else {
            throw new Exception("Parametre non supporte data.provider");
        }
    }

    @DataProvider
    public static Object[][] AllURIs() {
        return new Object[][]{
                {"http://localhost:5000"},
                {"https://rec-clickclack-api.herokuapp.com"},
                {"https://clickclack-api.herokuapp.com"},
        };
    }

    @DataProvider
    public static Object[][] LocalURI() {
        return new Object[][]{
                {"http://localhost:5000"},
        };
    }

    @DataProvider
    public static Object[][] QAURI() {
        return new Object[][]{
                {"https://rec-clickclack-api.herokuapp.com"},
        };
    }

    @DataProvider
    public static Object[][] PRODURI() {
        return new Object[][]{
                {"https://clickclack-api.herokuapp.com"},
        };
    }
}
