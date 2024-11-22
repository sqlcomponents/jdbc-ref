package com.techatpark;

import com.techatpark.model.Person;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;

/**
 * Persistence Puzzle
 *
 * We need to save and retrieve person object from java to RDBMS.
 * Let's Measure the fundamental approaches.
 *
 * Note: This is not about feature list of individual library / Framework.
 */
class PersistencePuzzleTest {

    private final PersistencePuzzle persistencePuzzle;

    PersistencePuzzleTest() {
        persistencePuzzle = new PersistencePuzzle(getDataSource());
    }

    @Test
    void test() throws SQLException {

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