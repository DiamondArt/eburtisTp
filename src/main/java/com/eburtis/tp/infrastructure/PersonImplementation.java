package com.eburtis.tp.infrastructure;

import com.eburtis.tp.application.PersonService;
import com.eburtis.tp.domain.person.Person;
import com.eburtis.tp.domain.person.PersonRepository;
import com.eburtis.tp.domain.person.PersonVo;
import com.eburtis.tp.exceptions.*;
import com.eburtis.tp.validator.PersonValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


/*******************************************************************
 * Person implementation
 * @author  Melissa Kouadio
 * @version 1.0
 *****************************************************************/
@Service
@Slf4j
public class PersonImplementation implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonImplementation(
            PersonRepository personRepository1) {
        this.personRepository = personRepository1;
    }
    /**
     * @param person
     * @return PersonVo
     */
    @Override
    public PersonVo save(PersonVo person) {
        List<String> errors = PersonValidator.validate(person);
        if (!errors.isEmpty()) {
            log.error("Person is not valid {}", person);
            throw new InvalidEntityException("Personne n'est pas valide", ErrorCodes.PERSON_NOT_VALID, errors);
        }

        return PersonVo.fromEntity(
                personRepository.save(
                        PersonVo.toEntity(person)
                )
        );
    }

    /**
     * @return List<Person>
     */
    @Override
    public List<PersonVo> findAll() {
        return personRepository.findAll().stream()
                .map(PersonVo::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * @param idPerson
     * @return PersonVo
     */
    @Override
    public PersonVo findById(Long idPerson) {
        if (idPerson == null) {
            log.error("Person ID is null");
            return null;
        }
        return personRepository.findById(idPerson).map(PersonVo::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune personne avec l'ID = " + idPerson + " n' ete trouve dans la BDD",
                        ErrorCodes.PERSON_NOT_FOUND)
        );
    }

    /**
     * @param idPerson
     * @param person
     * @return PersonVo
     */
    @Override
    public PersonVo updatePerson(Long idPerson, PersonVo person) {

        Optional<Person> findPerson = personRepository.findById(idPerson);

        if (!findPerson.isPresent()) {
            throw new EntityNotFoundException("No value present", ErrorCodes.PERSON_NOT_FOUND);
        }

        List<String> errors = PersonValidator.validate(person);
        if (!errors.isEmpty()) {
            log.error("Person is not valid {}", person);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.PERSON_NOT_VALID, errors);
        }

        PersonVo personDt = PersonVo.fromEntity(personRepository.findById(idPerson).get());

        if(Objects.nonNull(person.getFirstname()) &&!"".equalsIgnoreCase(person.getFirstname())){
            personDt.setFirstname(person.getFirstname());
        }
        if(Objects.nonNull(person.getLastname()) &&!"".equalsIgnoreCase(person.getLastname())){
            personDt.setLastname(person.getLastname());
        }
        if(Objects.nonNull(person.getAge())){
            personDt.setAge(person.getAge());
        }
        if(Objects.nonNull(person.getDepartment())){
            personDt.setDepartment(person.getDepartment());
        }

        return PersonVo.fromEntity(
                personRepository.save(
                        PersonVo.toEntity(personDt)
                )
        );
    }

    /**
     * @param idPerson
     */
    @Override
    public void delete(Long idPerson) {

        Optional<Person> person = personRepository.findById(idPerson);
        if (!person.isPresent()) {
            log.error("Person ID is null");
            throw new EntityNotFoundException("Person not available ", ErrorCodes.PERSON_NOT_FOUND);
        }
        personRepository.deleteById(idPerson);
    }


}
