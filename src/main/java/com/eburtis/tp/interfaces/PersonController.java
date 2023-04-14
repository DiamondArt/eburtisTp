package com.eburtis.tp.interfaces;

import com.eburtis.tp.application.PersonService;
import com.eburtis.tp.domain.person.Person;
import com.eburtis.tp.domain.person.PersonVo;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*******************************************************************
 * Person controller
 * @author Melissa Kouadio
 * @version 1.0
 *****************************************************************/
@RestController
@RequestMapping("/v1/api/rest/person")
@CrossOrigin("*")
public class PersonController {

    private final PersonService personService;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Get only person by id
     * @param idPerson
     * @return Person
     **/
    /**
     * @param idPerson
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<PersonVo> fetchPersonById(@PathVariable("id") Long idPerson) {

        logger.info("Inside fetchPersonByID PersonController ");
        PersonVo ps = personService.findById(idPerson);
        return ResponseEntity.status(HttpStatus.OK).body(ps);

    }

    /**
     * Fetch all persons
     * @return List of Persons
     **/
    @GetMapping()
    public ResponseEntity<List<PersonVo>> fetchAllPerson() {
        logger.info("Inside fetchAllPerson: fetch All Person  ");
        List<PersonVo> personList = personService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(personList);

    }

    /**
     * Delete a person by id
     * @param idPerson
     * @return String success
     ***/
    @DeleteMapping("{id}")
    public ResponseEntity deletePerson(@PathVariable("id") Long idPerson) {

        logger.info("Inside delete Person PersonController ");
        personService.delete(idPerson);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * Create a new person
     * @param person
     **/
    @PostMapping()
    public ResponseEntity<PersonVo> savePerson(@Validated @RequestBody PersonVo person) {
        logger.info("Inside SavePerson PersonController ");
        PersonVo ps = personService.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(ps);
    }

    /**
     * Update person by id
     * @param idPerson
     * @param person
     ***/
    @PutMapping("{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long idPerson, @Valid @RequestBody PersonVo person) {
        logger.info("Inside updatePerson PersonController ");
        Person ps = personService.updatePerson(idPerson, person);
        return ResponseEntity.status(HttpStatus.OK).body(ps);
    }
}
