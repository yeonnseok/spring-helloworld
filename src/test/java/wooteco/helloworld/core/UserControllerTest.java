package wooteco.helloworld.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import wooteco.helloworld.HelloApplication;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HelloApplication.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    /**
     * UserController을 빈으로 등록하기
     */
    @DisplayName("UserController가 빈으로 등록되었는지 확인")
    @Test
    void test1() {
        assertThat(userController).isNotNull();
    }

    /**
     * UserService를 빈으로 등록하기
     */
    @DisplayName("UserService이 빈으로 등록되었는지 확인")
    @Test
    void test2() {
        assertThat(userService).isNotNull();
    }

    /**
     * UserController에 UserService를 주입하기
     */
    @DisplayName("UserController에 UserService이 주입되었는지 확인")
    @Test
    void test3() {
        assertThat(userController.retrieveUsers()).isNotNull();
    }
}
