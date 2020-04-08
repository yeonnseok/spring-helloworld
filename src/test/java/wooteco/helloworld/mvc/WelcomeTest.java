package wooteco.helloworld.mvc;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import wooteco.helloworld.domain.User;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WelcomeTest {
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("get 요청을 보내고 문자열 응답 받기")
    @Test
    void test1() {
        given().
                log().all().
        when().
                get("/welcome").
        then().
                log().all().
                statusCode(200).
                body(is("welcome"));
    }

    @DisplayName("get 요청을 보내고 페이지 응답 받기")
    @Test
    void test2() {
        given().
                log().all().
        when().
                get("/welcome-page").
        then().
                log().all().
                statusCode(200).
                body(containsString("웰컴 페이지"));
    }

    @DisplayName("get 요청을 보내고 json 형식으로 응답 받기")
    @Test
    void test3() {
        User user =
                given().
                        log().all().
                when().
                        get("/welcome-user").
                then().
                        log().all().
                        statusCode(200).
                        extract().as(User.class);

        assertThat(user.getName()).isEqualTo("브라운");
    }

    @DisplayName("get 요청을 보내고 페이지에서 사용할 값 포함하여 페이지 응답 받기")
    @Test
    void test4() {
        String name = "brown";

        given().
                log().all().
                queryParam("name", name).
        when().
                get("/welcome-page").
        then().
                log().all().
                statusCode(200).
                body(containsString("웰컴 페이지")).
                body(containsString("welcome brown"));
    }
}
