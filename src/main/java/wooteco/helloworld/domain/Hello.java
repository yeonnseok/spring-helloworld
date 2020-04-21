package wooteco.helloworld.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Hello {

    @Id
    private Long id;
    private String name;
    private LocalDateTime createdAt;

    public Hello(final String name) {
        this.name = name;
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
