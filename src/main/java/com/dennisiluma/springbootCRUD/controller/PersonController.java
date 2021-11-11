package com.dennisiluma.springbootCRUD.controller;

import com.dennisiluma.springbootCRUD.model.Person;
import com.dennisiluma.springbootCRUD.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@NonNull @RequestBody Person person) {//requestbody annotation means we'll be coleting an object of type person(meaning the objet will have person attribute)
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){//@pathvariable("id") means we are collecting the value stored as id in path and mapping it or putting it as UUID id
        return personService.getPersonById(id)
                .orElse(null);
    }
    @DeleteMapping(path = "{id}")
    public void deletePerson(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }
    @PutMapping(path = "{id}/edit")
    public void updatePerson(@PathVariable("id") UUID id, @Validated @NonNull @RequestBody Person newPerson){ //we are saying the newPerson should not be null and valid
        personService.updatePerson(id, newPerson);

    }
}
