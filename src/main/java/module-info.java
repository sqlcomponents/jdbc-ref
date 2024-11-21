module my.module {
    requires java.base;
    requires java.sql;
    requires java.naming;

    requires spring.context;
    requires spring.jdbc;

    exports com.techatpark.practices.jdbc.store;
}