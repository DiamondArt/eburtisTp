package com.eburtis.tp.application;

import com.eburtis.tp.domain.Person;
import com.eburtis.tp.domain.PersonRepository;
import com.eburtis.tp.exceptions.EntityException;
import com.eburtis.tp.infrastructure.PersonImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/*******************************************************************
 * Person Service
 * @author  Melissa Kouadio
 * @version 1.0
 *****************************************************************/
@Service
public class PersonService implements PersonImplementation {

    @Autowired
    private PersonRepository personRepository;
    /**
     * @param person
     * @return Person
     */
    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    /**
     * @return List<Person>
     */
    @Override
    public List<Person> fetchPersonList() {
        return personRepository.findAll();
    }

    /**
     * @param idPerson
     * @return Person
     * @throws EntityException
     */
    @Override
    public Person fetchPerson(Long idPerson) throws EntityException {
        Optional<Person> person = personRepository.findById(idPerson);
        if (!person.isPresent()) {
            throw new EntityException("Person not found");
        }
        return person.get();
    }

    /**
     * @param idPerson
     * @param persons
     * @return Person
     * @throws EntityException
     */
    @Override
    public Person updatePerson(Long idPerson, Person persons) throws EntityException {
        Optional<Person> findPerson = personRepository.findById(idPerson);

        if (!findPerson.isPresent()) {
            throw new EntityException("No value present");
        }

        Person personDt = personRepository.findById(idPerson).get();
        if(Objects.nonNull(persons.getFirstname()) &&!"".equalsIgnoreCase(persons.getFirstname())){
            personDt.setFirstname(persons.getFirstname());
        }
        if(Objects.nonNull(persons.getLastname()) &&!"".equalsIgnoreCase(persons.getLastname())){
            personDt.setLastname(persons.getLastname());
        }
        if(Objects.nonNull(persons.getAge())){
            personDt.setAge(persons.getAge());
        }
        if(Objects.nonNull(persons.getDepartment())){
            personDt.setDepartment(persons.getDepartment());
        }
        return personRepository.save(personDt);
    }

    /**
     * @param idPerson
     * @throws EntityException
     */
    @Override
    public void deletePerson(Long idPerson) throws EntityException {
        Optional<Person> person = personRepository.findById(idPerson);
        if (!person.isPresent()) {
            throw new EntityException("Person not available ");
        }
        personRepository.deleteById(idPerson);
    }
}
