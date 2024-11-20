package com.techatpark.practices.jdbc.store;

import com.techatpark.practices.jdbc.model.MyEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.LineSegment;

import java.sql.SQLException;

class MyEntityStoreTest {

    @Test
    void testRoundTrip() throws SQLException {

        MyEntityStore movieMaker = new MyEntityStore();

        movieMaker.delete();

        MyEntity myEntity = new MyEntity(1L, "Hello");

        movieMaker.create(myEntity);

        myEntity = movieMaker.list().get(0);

        Assertions.assertEquals(myEntity.theValue(), "Hello");


    }
}