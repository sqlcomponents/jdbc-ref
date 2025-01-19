package org.sqlcomponents;

import org.sqlcomponents.model.Person;

import java.util.Optional;

import javax.sql.DataSource;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Explore various persistence approaches in Java with hands-on examples.
 */
public class PersistencePuzzle {

    private static final String INSERT_SQL = "INSERT INTO person(name) VALUES (?)";
    private static final String SELECT_SQL = "SELECT id,name from person where id=?";

    private final DataSource dataSource;

    public PersistencePuzzle(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Person save(Person person) throws SQLException {
        // Insert and Get Generated Person Id
        long generatedId = -1;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection
                     .prepareStatement(INSERT_SQL,
                             Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, person.name());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                generatedId = rs.getLong(1);
            }
        }
        // Fetch Person from Generated Person Id
        return findById(generatedId).orElse(null);
    }

    Optional<Person> findById(Long id) throws SQLException {
        Person person = null;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection
                     .prepareStatement(SELECT_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                person = new Person(rs.getLong(1),
                        rs.getString(2));
            }
        }
        return Optional.ofNullable(person);
    }

}
