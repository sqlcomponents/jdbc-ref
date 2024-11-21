package com.techatpark.practices.jdbc.store;

import com.techatpark.practices.jdbc.model.Person;

import java.util.Optional;

import javax.sql.DataSource;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 1. Only You, JDK and JDBC Drive (Postgres)
 *
 * ./mvnw dependency:tree -Dscope=compile
 *
 * 2. Use Anything JAVA permits
 * 3. You use this in Spring Boot, Quarkus or Anything
 */
public class PersistencePuzzle {

    private final DataSource dataSource;

    public PersistencePuzzle(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Person save(Person person) throws SQLException {
        Person created = null;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection
                     .prepareStatement("INSERT INTO person(name) VALUES (?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, person.name());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                created = findById(resultSet.getLong(1)).get();
            }
        }
        return created;
    }

    Optional<Person> findById(Long id) throws SQLException {
        Person person = null;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection
                     .prepareStatement("SELECT id,name from person where id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                person = new Person(resultSet.getLong(1),
                        resultSet.getString(2));
            }
        }
        return Optional.ofNullable(person);
    }

}
