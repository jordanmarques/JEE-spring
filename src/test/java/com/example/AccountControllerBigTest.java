package com.example;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = J2ETestsApplication.class)
@WebIntegrationTest("server.port=9898")
public class AccountControllerBigTest {

    @Before
    public void setupRestAssured(){
        RestAssured.port = 9898;
    }

    @Test
    public void should_get_list_of_3_accounts(){
        when()
            .get("/accounts")
        .then()
            .log().all()
            .statusCode(200)
            .body("$", hasSize(3))
            .body("[0].balance", is(10));
    }

    @Test
    public void should_insert_an_account(){
        int balance = 100;
        Account account = Account.builder().balance(balance).build();
        given()
            .contentType(ContentType.JSON)
            .body(account)

        .when()
            .post("/accounts")
        .then()
            .log().all()
            .statusCode(201)
            .body("balance", is(balance));
    }
}
