package com.techatpark.practices.jdbc.store;

import com.techatpark.practices.jdbc.model.Person;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 1. Only You, JDK and JDBC Drive (Postgres)
 *
 * ./mvnw dependency:tree -Dscope=compile
 *
 * 2. Use Anything JAVA permits
 * 3. You use this in Spring Boot, Quarkus or Anything. (Framework/Library Independent)
 */
@Service
public class PersistencePuzzle {

    private final JdbcClient jdbcClient;

    public PersistencePuzzle(final JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Person save(Person person) {

        String sql = "insert into person(name) values (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .param(1,person.name())
                .update(keyHolder,  "id" );

        return findById(keyHolder.getKey().longValue()).get();
    }

    Optional<Person> findById(Long id) {
        return jdbcClient.sql("SELECT id,name from person where id=?")
                .param(id)
                .query((resultSet, rowNum) -> new Person(resultSet.getLong(1),
                        resultSet.getString(2)))
                .optional();
    }

}
