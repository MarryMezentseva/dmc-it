package com.airplainsoft.it;

import com.airplainsoft.it.dto.RestResponse;
import com.airplainsoft.it.dto.UserDto;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.*;

public class UserControllerIT {
    private final static int PORT = 8307;
    private final static String BASE_URI = "http://localhost";
    private final static String BASE_PATH = "/api";
    private final static String USER_URL = "/user";
    private final static String USER_BY_ID = USER_URL + "/%s";

    @BeforeMethod
    void setUp() {
        RestAssured.port = PORT;
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
    }

    @Test
    public void shouldReturnUserById() {
        //given
        UserDto createdUserDto = createUser();
        //when
        String createdUserUrl = String.format(USER_BY_ID, createdUserDto.getId());
        UserDto userFromResponse = performGet(createdUserUrl, new TypeRef<RestResponse<UserDto>>() {
        }, 200).getPayload();
        //then
        assertNotNull(userFromResponse);
        assertEquals(userFromResponse.getId(), createdUserDto.getId());
        assertEquals(userFromResponse.getFirstName(), createdUserDto.getFirstName());
        assertEquals(userFromResponse.getLastName(), createdUserDto.getLastName());
        assertEquals(userFromResponse.getEmail(), createdUserDto.getEmail());
        assertNull(createdUserDto.getJobPositions());
        //after
        performDelete(createdUserUrl, 200);
    }

    @Test
    public void shouldReturn404WhenGetUserById(){
        //given
        Integer nonexistentId = Integer.MAX_VALUE;
        //when
        UserDto userFromResponse = performGet(String.format(USER_BY_ID, nonexistentId), new TypeRef<RestResponse<UserDto>>() {
        }, 404).getPayload();
        //then
        assertNull(userFromResponse);
    }

    protected <T> RestResponse<T> performGet(String url, TypeRef<RestResponse<T>> typeRef, int expectedStatus) {
        Response response = given()
                .when()
                .get(url);
        response.then()
                .statusCode(expectedStatus);
        return response.as(typeRef);
    }

    protected <T, B> RestResponse<T> performPost(B body, String url, TypeRef<RestResponse<T>> typeRef, int expectedStatus) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url);
        response.then()
                .statusCode(expectedStatus);
        return response.as(typeRef);
    }

    private UserDto createUser() {
        RestResponse<UserDto> restResponse = performPost(new UserDto("Fisher", "Pug", "fisher@pug.dog", null, null),
                USER_URL, new TypeRef<RestResponse<UserDto>>() {
                }, 200);
        return restResponse.getPayload();
    }

    protected void performDelete(String url, int expectedStatus) {
        Response response = given()
                .when()
                .delete(url);
        response.then()
                .statusCode(expectedStatus);
    }
}
