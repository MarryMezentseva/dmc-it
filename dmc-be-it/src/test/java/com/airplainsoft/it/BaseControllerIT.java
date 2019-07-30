package com.airplainsoft.it;

import static io.restassured.RestAssured.given;

import com.airplainsoft.it.dto.RestResponse;
import com.airplainsoft.it.dto.UserDto;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Map;

public abstract class BaseControllerIT {

  protected final static int PORT = 8307;
  protected final static String BASE_URI = "http://localhost";
  protected final static String BASE_PATH = "/api";

  protected final static String USER_URL = "/user";
  protected final static String USER_BY_ID = USER_URL + "/%s";

  protected final static String DEVICE_URL = "/device";

  protected <T> RestResponse<T> performGet(String url, TypeRef<RestResponse<T>> typeRef,
      int expectedStatus) {
    Response response = given()
        .when()
        .get(url);
    response.then()
        .statusCode(expectedStatus);
    return response.as(typeRef);
  }

  protected <T> RestResponse<T> performGet(String url, TypeRef<RestResponse<T>> typeRef,
      int expectedStatus, Map<String, ?> urlParam) {
    Response response = given()
        .params(urlParam)
        .when()
        .get(url);
    response.then()
        .statusCode(expectedStatus);
    return response.as(typeRef);
  }

  protected <T, B> RestResponse<T> performPost(B body, String url, TypeRef<RestResponse<T>> typeRef,
      int expectedStatus) {
    Response response = given()
        .contentType(ContentType.JSON)
        .body(body)
        .when()
        .post(url);
    response.then()
        .statusCode(expectedStatus);
    return response.as(typeRef);
  }

  protected UserDto createUser(String firstName, String lastName, String email) {
    RestResponse<UserDto> restResponse = performPost(
        new UserDto(firstName, lastName, email, null, null),
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
