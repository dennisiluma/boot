package com.dennisiluma.springbootCRUD.repository;

import com.dennisiluma.springbootCRUD.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(Person person);
    List<Person> selectAllPerson();
     int deletePerson(UUID id);
     int updatePerson(UUID id,Person person);
     Optional<Person> selectPersonById(UUID id);
}
