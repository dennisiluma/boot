package com.dennisiluma.springbootCRUD.service;

import com.dennisiluma.springbootCRUD.model.Person;
import com.dennisiluma.springbootCRUD.repository.FakePersonDataAccessService;
import com.dennisiluma.springbootCRUD.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    /**@Qualified("fakeDao")means we are injecting a repository labeled "fakeDao" bacause there can be more than one repository in complex projects */
    public PersonService(@Qualifier("postgres") PersonDao personDao) { //@Qualifier is used to distinguished or label something incase they are more than one service, we have to label each and specify the one to use in the repository class
        /**we can use "PersonDao personDao" above instead of "FakePersonDataAccessService personDao". it will still work*/
        this.personDao = personDao;
    }

    public int addPerson(Person person){
        return personDao.insertPerson(person);
    }
    public List<Person> getAllPeople(){
        return personDao.selectAllPerson();
    }
    /**Optional<Person> return type means it can return either Person or null. It's a way to safely return objects that are nullable</>**/
    public Optional<Person> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }
    public int deletePerson(UUID id){
        return personDao.deletePerson(id);
    }
    public int updatePerson(UUID id, Person newPerson){
        return personDao.updatePerson(id, newPerson);
    }
}
