module my.module {
    requires java.base;
    requires java.sql;

    opens com.techatpark.practices.jdbc.store;
    opens com.techatpark.practices.jdbc.model;
}