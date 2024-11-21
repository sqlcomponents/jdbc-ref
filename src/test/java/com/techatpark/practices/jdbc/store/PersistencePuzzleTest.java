package com.techatpark.practices.jdbc.store;

import com.techatpark.practices.jdbc.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

/**
 * Persistence Puzzle
 *
 * We need to save and retrieve person object from java to RDBMS.
 * Lets Measure the fundamental approaches.
 *
 * Note: This is not about feature list comparison.
 */
@SpringBootTest
class PersistencePuzzleTest {

    @Autowired
    private PersistencePuzzle persistencePuzzle;

    @Test
    void test() throws SQLException {

        // The Puzzle
        Person person = persistencePuzzle.save(new Person(null,"Hello"));

        System.out.println(person);

    }
































































    private static PGSimpleDataSource getDataSource() {
        PGSimpleDataSource ds = new PGSimpleDataSource() ;
        ds.setServerName( "localhost" );
        ds.setDatabaseName( "postgres" );
        ds.setUser( "postgres" );
        ds.setPassword( "postgres123" );
        return ds;
    }
}