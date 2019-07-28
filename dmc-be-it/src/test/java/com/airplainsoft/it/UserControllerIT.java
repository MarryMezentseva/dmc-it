package com.airplainsoft.it;

import com.airplainsoft.it.dto.RestResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

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

    protected <T> RestResponse<T> performGet(String url, TypeRef<RestResponse<T>> typeRef, int expectedStatus) {
        Response response = given()
                .when()
                .get(url);
        response.then()
                .statusCode(expectedStatus);
        return response.as(typeRef);
    }

}
