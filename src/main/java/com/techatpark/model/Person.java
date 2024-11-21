package com.techatpark.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(final String name) {
        this.name = name;
    }



    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Person[" +
                "id=" + id + ", " +
                "name=" + name + ']';
    }

}
