package com.techatpark.store;

import com.techatpark.model.Person;
import com.techatpark.repository.PersonRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * 1. Only You, JDK and JDBC Driver (Postgres) + Little Etc
 *
 * mvn dependency:tree -Dscope=compile
 *
 * 2. Use Anything YOUR JAVA permits
 * 3. You use this in Only Spring Boot,  (Framework Dependent)
 */
@Service
public class PersistencePuzzle {

    private final PersonRepository personRepository;

    public PersistencePuzzle(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

}
