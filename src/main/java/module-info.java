module my.module {
    requires java.naming;

    requires spring.jdbc;
    requires spring.context;
    requires spring.data.commons;
    requires jakarta.persistence;

    exports com.techatpark.store;
    exports com.techatpark.model;
    exports com.techatpark.repository;
}