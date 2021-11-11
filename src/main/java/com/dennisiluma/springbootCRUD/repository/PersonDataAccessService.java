package com.dennisiluma.springbootCRUD.repository;

import com.dennisiluma.springbootCRUD.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{
    @Override
    public int insertPerson(Person person) {
        return 0;
    }

    @Override
    public List<Person> selectAllPerson() {
        return List.of(new Person(UUID.randomUUID(), "hehehehe"));
    }

    @Override
    public int deletePerson(UUID id) {
        return 0;
    }

    @Override
    public int updatePerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return Optional.empty();
    }
}
