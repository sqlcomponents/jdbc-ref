module my.module {
    requires java.base;
    requires java.sql;
    requires org.locationtech.jts;
    requires org.postgresql.jdbc;

    opens com.techatpark.practices.jdbc.store;
    opens com.techatpark.practices.jdbc.model;
}