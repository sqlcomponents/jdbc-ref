package com.techatpark.practices.jdbc.store;

import com.techatpark.practices.jdbc.model.MyEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Persistence Puzzle
 *
 * We need to save and retrieve person object from java to RDBMS.
 *
 * Lets Measure the fundamental approaches
 */
public class MyEntityService {

    private final DataSource dataSource;

    public MyEntityService(final DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public MyEntity save(MyEntity myEntity) throws SQLException {
        MyEntity created = null;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection
                     .prepareStatement("INSERT INTO my_entity(the_value) VALUES (?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, myEntity.theValue());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                created = findById(resultSet.getLong(1)).get();
            }
        }
        return created;
    }

    Optional<MyEntity> findById(Long id) throws SQLException {
        MyEntity myEntity = null;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection
                     .prepareStatement("SELECT id,the_value from my_entity where id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                myEntity = new MyEntity(resultSet.getLong(1),
                        resultSet.getString(2));
            }
        }
        return Optional.ofNullable(myEntity);
    }



}
