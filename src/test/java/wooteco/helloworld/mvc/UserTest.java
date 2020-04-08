package wooteco.helloworld.mvc;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import wooteco.helloworld.domain.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("query 포함하여 요청을 보내고 응답 결과에 포함 시키기")
    @Test
    void test5() {
        String name = "Brown";

        given().
                log().all().
        when().
                get("/users?name=" + name).
        then().
                log().all().
                statusCode(200).
                body(is("Hi " + name));
    }

    @DisplayName("body를 포함하여 post 요청을 보내고 응답 받기")
    @Test
    void test6() {
        User brown = new User("브라운", "brown@email.com", 32);

        given().
                log().all().
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body(brown).
        when().
                post("/users").
        then().
                log().all().
                statusCode(200).
                body(is("Hello " + brown.getName()));
    }

    /**
     * localhost:8080/post-form 페이지에서 요청보내는 것과 동일한 내용
     */
    @DisplayName("form형식으로 param을 포함하여 post 요청을 보내고 응답 받기")
    @Test
    void test7() {
        String name = "brown";

        given().
                log().all().
                contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8").
                formParam("name", name).
                formParam("email", "brown@email.com").
                formParam("age", 32).
        when().
                post("/users/form").
        then().
                log().all().
                statusCode(200).
                body(is("Hello " + name));
    }

    @DisplayName("path를 포함하여 요청을 보내고 응답 받기")
    @Test
    void test8() {
        given().
                log().all().
        when().
                get("/users/" + 1L).
        then().
                log().all().
                statusCode(200).
                body(is("user's id is " + 1L));
    }
}
