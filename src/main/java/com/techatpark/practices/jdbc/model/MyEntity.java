package com.techatpark.practices.jdbc.model;
import org.locationtech.jts.geom.LineSegment;

public record MyEntity(long id, LineSegment theValue) {
}
