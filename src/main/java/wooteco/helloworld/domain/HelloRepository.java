package wooteco.helloworld.domain;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HelloRepository extends CrudRepository<Hello, Long> {
    @Query("select count(1) from hello where name = :name")
    int countByName(@Param("name") String name);
}
