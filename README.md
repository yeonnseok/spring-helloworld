# ⭐️ 실습 - 초간단 스프링 프로젝트

- 환영 인사와 몇번째 방문인지를 알려주는 초간단 웹 애플리케이션
> `localhost:8080/hello?name=브라운` 으로 요청

![image.png](https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/2020-03-25T14%3A44%3A22.544image.png)

---
## 0. 실습 환경 구축하기
- 원활한 실습을 위해 수업 전에 실습 환경을 준비해 주세요.
- 환경 구축 간 어려움을 겪는다면 `#2기-레벨2질문방`을 활용해주세요.(자문 자답도 좋습니다. <span class=highlight>내가 모르는건 다른 크루들도 몰라요</span>)

- [https://github.com/woowacourse/spring-helloworld](https://github.com/woowacourse/spring-helloworld)에서 master clone 받기

---
## 1. HelloApplication 실행하기
- 별도의 코드작업 없이 바로 서버를 실행

![image.png](https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/2020-03-25T14%3A57%3A09.134image.png)

- 브라우저에서 `localhost: 8080`로 요청 후 페이지 확인 
![image.png](https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/2020-03-25T14%3A58%3A40.273image.png)

---
## 2. /hello 요청 후 응답
- 요청 / 응답 흐름도
![image.png](https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/2020-03-27T21%3A43%3A01.981image.png)

- HelloController 파일을 생성하여 코드를 작성
```java
package wooteco.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "") String name) {
        return "Hello " + name;
    }
}
```

- 브라우저에서 `localhost:8080/hello?name=브라운`으로 요청 후 페이지 확인
![image.png](https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/2020-03-25T15%3A03%3A58.223image.png)

---
## ** Spring Data Jdbc 설정 **
- Spring Data Jdbc 기능을 사용하기 위해 의존성 추가
- DB에 Table을 만들기 위해 schema 추가
- Table과 객체를 맵핑하기 위해 Entity Class 생성
- 쿼리를 메서드로 추상화하여 사용할 수 있게 도와주는 Repository 생성
![image.png](https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/2020-03-27T22%3A03%3A55.904image.png)

---
## 3. 테이블 정의
- resources/schema.sql 파일에 <span class=highlight>스키마 정의</sapn>

> resources/schema.sql
```
create table hello(
    id bigint auto_increment,
    name varchar(255) not null,
    created_at datetime,
    primary key(id)
);
```

---
## 4. Entity 클래스
- `Table`과 `객체`를 맵핑하기 위해 Entity Class 정의
- @Id를 통해 식별자 표시

```java
package wooteco.helloworld;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Hello {
    @Id
    private Long id;
    private String name;
    private LocalDateTime createdAt;

    public Hello(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
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
```

---
## 5. Repository 작성
- <span class=highlight>쿼리를 메서드로 추상화</span>하여 사용할 수 있게 도와줌
- 쿼리를 추가로 사용하고 싶은 경우 아래와 같이 직접 맵핑 가능

```java
package wooteco.helloworld;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HelloRepository extends CrudRepository<Hello, Long> {
    @Query("select count(1) from hello where name = :name")
    int countByName(@Param("name") String name);
}
```

- 많이 사용되는 insert, select, delete를 위한 메서드들은 기본으로 제공

---
## 6. Controller에서 Repository 사용하기
- Repository 객체를 직접 만들지 않아도  Spring(core)에서 객체들을 관리해줌
- 사실은 Controller도 직접 생성해주지 않았지만 잘 사용했음(Controller도 객체 관리 대상!)
- 모든 객체를 관리해주는 것은 아니고 표시(@애너테이션을 붙이는 등)를 한 클래스의 객체들만 관리
- **지금은 <span class=highlight>아~ 내가 직접 생성해 주지 않아도 객체를 관리해 줄 수도 있구나~</span> 정도만 이해해도 됨**

### 방법1 - @Autowired를 활용
```java
...

@RestController
public class HelloController {
    @Autowired
    private HelloRepository helloRepository;
    
    @GetMapping("/hello")
    ...
}
```
### 방법2 - 생성자를 활용
```java
...

@RestController
public class HelloController {
    private HelloRepository helloRepository;

    public HelloController(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @GetMapping("/hello")
    ...
}
```

---
## 7. 컨트롤러 기능 수정
- HelloController와 HelloRepository의 의존 관계를 생성자를 통해 설정
- name 값이 없으면 그냥 HelloWorld를 화면에 표시
- name 값이 있으면 몇번째 방문인지 알려줌

```java
package wooteco.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private HelloRepository helloRepository;

    public HelloController(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "") String name) {
        if (name.isEmpty()) {
            return "HelloWorld!";
        }

        helloRepository.save(new Hello(name));
        int count = helloRepository.countByName(name);
        return "Hello " + name + " " + count + "번째 방문입니다.";
    }
}
```

---
## 완성!
![image.png](https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/2020-03-25T14%3A44%3A22.544image.png)
