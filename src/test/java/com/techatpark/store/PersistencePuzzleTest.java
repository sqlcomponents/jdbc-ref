package com.techatpark.store;

import com.techatpark.model.Person;
import com.techatpark.tables.records.PersonRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
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

    @Test
    void test() throws SQLException {

        PersistencePuzzle persistencePuzzle = new PersistencePuzzle(getDataSource());

        // The Puzzle
        Person person = persistencePuzzle.save(new Person(null,"Hello"));
        System.out.println(person);

        DSLContext context = DSL.using(getDataSource(), SQLDialect.POSTGRES);

        PersonRecord personRecord = context.newRecord(com.techatpark.tables.Person.PERSON);

        personRecord.setName("Hello JooQ");

        personRecord.store();

        Result<Record> people = context.select()
                .from(com.techatpark.tables.Person.PERSON)
                .fetch();

        System.out.println(people);


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