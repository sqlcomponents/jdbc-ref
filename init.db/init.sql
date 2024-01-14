DROP TABLE IF EXISTS my_entity;

CREATE TABLE my_entity (
    id SERIAL NOT NULL,
    the_value VARCHAR(80),
    PRIMARY KEY ( id )
);