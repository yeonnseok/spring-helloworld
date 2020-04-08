package wooteco.helloworld.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@DataJdbcTest
@Transactional
public class UserRepositoryTest {
    private static final Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

    public static final String NAME = "브라운";
    public static final String NEW_NAME = "뉴브라운";

    @Autowired
    private UserRepository userRepository;

    @DisplayName("생성")
    @Test
    public void save() {
        User savedUser = userRepository.save(new User(NAME));

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo(NAME);
        assertThat(savedUser.getCreateAt()).isNotNull();
        assertThat(savedUser.getUpdateAt()).isNotNull();
    }

    @DisplayName("조회")
    @Test
    public void findById() {
        User savedUser = userRepository.save(new User(NAME));
        assertThat(savedUser.getId()).isNotNull();

        User persistUser = userRepository.findById(savedUser.getId()).orElseThrow(RuntimeException::new);
        assertThat(persistUser.getName()).isEqualTo(NAME);
    }

    @DisplayName("목록 조회")
    @Test
    public void findAll() {
        userRepository.save(new User(NAME));
        userRepository.save(new User(NEW_NAME));

        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(2);
    }

    @DisplayName("수정")
    @Test
    public void update() {
        User savedUser = userRepository.save(new User(NAME));
        savedUser.update(new User(NEW_NAME));
        User updatedUser = userRepository.save(savedUser);

        User persistUser = userRepository.findById(updatedUser.getId()).orElseThrow(RuntimeException::new);

        assertThat(persistUser.getName()).isEqualTo(NEW_NAME);
        assertThat(persistUser.getCreateAt()).isEqualTo(savedUser.getCreateAt());
    }

    @DisplayName("제거")
    @Test
    public void deleteById() {
        User savedUser = userRepository.save(new User(NAME));
        userRepository.deleteById(savedUser.getId());

        assertThatThrownBy(() -> {
            userRepository.findById(savedUser.getId()).orElseThrow(RuntimeException::new);
        }).isInstanceOf(RuntimeException.class);
    }

    @DisplayName("카운트")
    @Test
    public void countByName() {
        userRepository.save(new User(NAME));

        int count = userRepository.countByName(NAME);

        assertThat(count).isEqualTo(1);
    }
}
