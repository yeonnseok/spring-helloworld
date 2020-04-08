package wooteco.helloworld.jdbc;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String name;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public User() {
    }

    public User(String name) {
        this.name = name;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void update(User author) {
        this.name = author.name;
        this.updateAt = LocalDateTime.now();
    }
}
