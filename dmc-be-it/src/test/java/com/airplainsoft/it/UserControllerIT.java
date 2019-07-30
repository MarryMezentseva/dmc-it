package com.airplainsoft.it;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

import com.airplainsoft.it.dto.RestResponse;
import com.airplainsoft.it.dto.UserDto;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserControllerIT extends BaseControllerIT {

  @BeforeMethod
  void setUp() {
    RestAssured.port = PORT;
    RestAssured.baseURI = BASE_URI;
    RestAssured.basePath = BASE_PATH;
  }

  @Test
  public void shouldReturnUserById() {
    //given
    UserDto createdUserDto = createUser("Fisher", "Pug", "fisher@pug.dog");
    //when
    String createdUserUrl = String.format(USER_BY_ID, createdUserDto.getId());
    UserDto userFromResponse = performGet(createdUserUrl, new TypeRef<RestResponse<UserDto>>() {
    }, 200).getPayload();
    //then
    assertNotNull(userFromResponse);
    assertEquals(createdUserDto, userFromResponse);
    //after
    performDelete(createdUserUrl, 200);
  }

  @Test
  public void shouldReturn404WhenGetUserById() {
    //given
    Integer nonexistentId = Integer.MAX_VALUE;
    //when
    UserDto userFromResponse = performGet(String.format(USER_BY_ID, nonexistentId),
        new TypeRef<RestResponse<UserDto>>() {
        }, 404).getPayload();
    //then
    assertNull(userFromResponse);
  }

  @Test
  public void shouldReturnAllUsers() {
    //given
    UserDto user1 = createUser("Fisher1", "Pug", "fisher1@pug.dog");
    UserDto user2 = createUser("Fisher2", "Pug", "fisher2@pug.dog");
    Map<String, Object> urlParams = new HashMap<>();
    urlParams.put("page", 0);
    urlParams.put("size", 50);
    //when
    List<UserDto> usersFromResponse = performGet(USER_URL,
        new TypeRef<RestResponse<List<UserDto>>>() {
        }, 200, urlParams).getPayload();
    //then
    assertTrue(1 < usersFromResponse.size());
    assertTrue(usersFromResponse.contains(user1));
    assertTrue(usersFromResponse.contains(user2));
  }

  @Test
  public void shouldDeleteUserById() {
    //given
    UserDto createdUserDto = createUser("Fisher", "Pug", "fisher@pug.dog");
    //when
    performDelete(String.format(USER_BY_ID, createdUserDto.getId()), 200);
    //then
    assertNull(performGet(String.format(USER_BY_ID, createdUserDto.getId()),
        new TypeRef<RestResponse<UserDto>>() {
        }, 404).getPayload());
  }
}