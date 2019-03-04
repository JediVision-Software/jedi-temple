package com.forcelate.configuration;

import io.restassured.RestAssured;


public class ApplicationRestAssured {

    public void init() {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8484;
        RestAssured.basePath = "/api";
    }
}
