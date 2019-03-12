package com.forcelate.tests.resource;

import com.forcelate.acceptance.domain.processing.CaseAnnotation;
import com.forcelate.configuration.runner.AbstractResourceRunner;
import com.forcelate.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static com.forcelate.acceptance.domain.processing.HttpTypes.GET;
import static com.forcelate.acceptance.domain.processing.HttpTypes.POST;

@Slf4j
public class UserResource extends AbstractResourceRunner {

    private UserUtils userUtils = new UserUtils();

    @CaseAnnotation(endpoint = "/api/user/{userId}", httpType = GET, description = "Find User by id")
    @Test
    public void findOne()  {
        when()
                    .get("/api/user/" + 1L)
        .then()
                    .statusCode(200)
                    .assertThat()
                    .body("age", equalTo(14));

    }


    @CaseAnnotation(endpoint = "/api/user", httpType = POST, description = "Add new User")
    @Test
    public void add() throws IOException {
        given()
                    .contentType("application/json")
                    .body(userUtils.createUser())
        .when()
                    .post("/api/user")
        .then()
                    .statusCode(200);
    }
}
