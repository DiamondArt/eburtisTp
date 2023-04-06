package com.eburtis.tp.interfaces;

import com.eburtis.tp.application.PersonService;
import com.eburtis.tp.domain.Person;
import com.eburtis.tp.exceptions.EntityException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/rest/person")
@CrossOrigin("*")
public class PersonController {

    @Autowired
    private PersonService personService;
    private final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("{id}")
    public Person fetchPersonById(@PathVariable("id") Long idPerson) throws EntityException {

        LOGGER.info("Inside fetchPersonByID PersonController ");
        return personService.fetchPerson(idPerson);
    }

    @GetMapping()
    public List<Person> fetchAllPerson(){
        LOGGER.info("Inside fetchAllPerson: fetch All Person  ");
        return personService.fetchPersonList();
    }

    @DeleteMapping("{id}")
    public String deletePerson(@PathVariable("id") Long idPerson) throws EntityException {

        LOGGER.info("Inside delete Person PersonController ");
        personService.deletePerson(idPerson);
        return "Success";
    }

    @PostMapping()
    public Person savePerson(@Validated @RequestBody Person person) {
        LOGGER.info("Inside SavePerson PersonController ");
        return personService.savePerson(person);
    }

    @PutMapping("{id}")
    public Person updatePerson(@PathVariable("id") Long idPerson, @Valid @RequestBody Person person) throws EntityException{
        LOGGER.info("Inside updatePerson PersonController ");
        return  personService.updatePerson(idPerson, person);
    }
}
