package com.techatpark;

import com.techatpark.model.Person;
import com.techatpark.store.PersistencePuzzle;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

/**
 * Persistence Puzzle
 *
 * We need to save and retrieve person object from java to RDBMS.
 * Let's Measure the fundamental approaches.
 *
 * Note: This is not about feature list of individual library / Framework.
 */
@SpringBootTest
class PersistencePuzzleTest {

    @Autowired
    private PersistencePuzzle persistencePuzzle;

    @Test
    void test() {

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