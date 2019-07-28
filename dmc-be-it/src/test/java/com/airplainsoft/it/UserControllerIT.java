package com.airplainsoft.it;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserControllerIT {
    private final static int PORT = 8307;
    private final static String BASE_URI = "http://localhost";
    private final static String BASE_PATH = "/api";
    private final static String USER_URL = "/user";

    @BeforeMethod
    void setUp() {
        RestAssured.port = PORT;
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
    }

    @Test
    public void shouldReturnUserById() {
        //TODO implementation
    }

}
