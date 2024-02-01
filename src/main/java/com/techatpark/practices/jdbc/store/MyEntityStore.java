package com.techatpark.practices.jdbc.store;

import com.techatpark.practices.jdbc.model.MyEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.postgresql.geometric.PGlseg;
import org.postgresql.geometric.PGpoint;
import org.locationtech.jts.geom.LineSegment;

public class MyEntityStore {

    public void create(MyEntity myEntity) throws SQLException {
        Connection connection = getConnection();
        String insertQuery = "INSERT INTO my_entity(the_value) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        // Value as LOCATION TECH LSEG => Postgress LSE

        PGpoint pGpoint1 = new PGpoint(myEntity.theValue().p0.x, myEntity.theValue().p0.y);
        PGpoint pGpoint2 = new PGpoint(myEntity.theValue().p1.x, myEntity.theValue().p1.y);
        PGlseg pGlseg = new PGlseg(pGpoint1,pGpoint2);

        preparedStatement.setObject(1, pGlseg);
        preparedStatement.execute();
    }


    public List<MyEntity> list() throws SQLException {
        List<MyEntity> myEntities = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,the_value from my_entity");

            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                // Value as Postgress LSE => LOCATION TECH LSEG
                PGlseg pGlseg = (PGlseg) rs.getObject(2);
                LineSegment lineSegment = new LineSegment(pGlseg.point[0].x,pGlseg.point[0].y,pGlseg.point[1].x,pGlseg.point[1].y);

                myEntities.add(new MyEntity(rs.getLong(1), lineSegment));
            }

        return myEntities;
    }

    public void delete() throws SQLException {
        Connection connection = getConnection();
        connection.prepareStatement("DELETE FROM my_entity").execute();
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres123");
    }


}
