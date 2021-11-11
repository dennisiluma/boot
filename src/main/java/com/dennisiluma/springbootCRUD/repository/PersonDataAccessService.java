package com.dennisiluma.springbootCRUD.repository;

import com.dennisiluma.springbootCRUD.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(Person person) {

        return jdbcTemplate.update("INSERT INTO person (id, name) VALUES(?,?)",
                new Object[] { UUID.randomUUID(), person.getName() });
    }

    @Override
    public List<Person> selectAllPerson() {
        final String sql = "SELECT id, name FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> { //query returns a list
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }

    @Override
    public int deletePerson(UUID id) {
        Optional<Person> person = selectPersonById(id);
        if (person.isEmpty()) {
            return 0;
        }
        return jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    @Override
    public int updatePerson(UUID id, Person newPerson) {

        return selectPersonById(id) //query the db to check if the id exist
                .map(p -> {
                    int result = jdbcTemplate.update("UPDATE person SET name=? WHERE id=?",
                            new Object[]{newPerson.getName(), p.getId()});
                    if (result > 0) {
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sql,
                new Object[]{id}, //if we have more arguments we can pass them here because it's an array of object
                (resultSet, i) -> { //queryForObject doesn't returns a list
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    return new Person(personId, name);
                });
        return Optional.ofNullable(person);
    }
}
