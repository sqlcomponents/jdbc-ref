package com.techatpark.practices.jdbc.store;

import com.techatpark.practices.jdbc.model.MyEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class MyEntityServiceTest {

    @Test
    void testRoundTrip() throws SQLException {

        MyEntityService movieMaker = new MyEntityService();

        movieMaker.delete();

        MyEntity myEntity = new MyEntity(1L, "Hello");

        movieMaker.create(myEntity);

        myEntity = movieMaker.list().get(0);

        Assertions.assertEquals(myEntity.theValue(), "Hello");


    }
}