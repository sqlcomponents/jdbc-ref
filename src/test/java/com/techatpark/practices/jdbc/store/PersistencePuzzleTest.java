package com.techatpark.practices.jdbc.store;

import com.techatpark.practices.jdbc.model.MyEntity;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;


/**
 * Persistence Puzzle
 *
 * We need to save and retrieve person object from java to RDBMS.
 * Lets Measure the fundamental approaches
 */
class MyEntityServiceTest {

    @Test
    void testPuzzle() throws SQLException {

        MyEntityService myEntityService = new MyEntityService(getDataSource());

        System.out.println(myEntityService.save(new MyEntity(null,"Hello")));

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