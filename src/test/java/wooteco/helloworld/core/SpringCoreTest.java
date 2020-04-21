package wooteco.helloworld.core;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * # 실습내용
 * 동시에 오는 요청이 정상동작 하도록 수정하세요.
 *
 * # 방법
 * 1. SpringCoreService빈을 Component Scan 할 수 있도록 위치를 옮겨 주세요.
 * 2. SpringCoreService의 상태를 제거해주세요.
 */
@Execution(ExecutionMode.CONCURRENT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringCoreTest {
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void myName1() {
        myNameRequest("브라운");
    }

    @Test
    void myName2() {
        myNameRequest("브라운2");
    }

    private void myNameRequest(String name) {
        given().
                log().all().
        when().
                get("/my-name?name=" + name).
        then().
                log().all().
                body(is("My name is " + name)).
                statusCode(200);
    }
}