package com.techatpark.practices.jdbc.store;

import com.techatpark.practices.jdbc.model.MyEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class MyEntityStoreTest {

    @Test
    void testRoundTrip() throws SQLException {

        MyEntityStore movieMaker = new MyEntityStore();

        movieMaker.delete();

        MyEntity myEntity = new MyEntity(1, "Bahubali");

        movieMaker.create(myEntity);

        myEntity = movieMaker.list().get(0);

        Assertions.assertTrue(ObjectUtils.allNotNull(myEntity.id(),myEntity.theValue()));


    }
}