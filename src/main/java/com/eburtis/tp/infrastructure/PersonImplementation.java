package com.eburtis.tp.infrastructure;

import com.eburtis.tp.domain.Person;
import com.eburtis.tp.exceptions.EntityException;

import java.util.List;

public interface PersonImplementation {

    public Person savePerson(Person person);

    List<Person> fetchPersonList();

    Person fetchPerson(Long idPerson) throws EntityException;

    void deletePerson(Long idPerson) throws EntityException;

    Person updatePerson(Long idPerson, Person person) throws EntityException;
}
