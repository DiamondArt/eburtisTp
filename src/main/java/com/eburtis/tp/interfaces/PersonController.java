package com.eburtis.tp.interfaces;

import com.eburtis.tp.application.PersonService;
import com.eburtis.tp.domain.person.Person;
import com.eburtis.tp.domain.person.PersonVo;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*******************************************************************
 * Person controller
 * @author  Melissa Kouadio
 * @version 1.0
 *****************************************************************/
@RestController
@RequestMapping("/v1/api/rest/person")
@CrossOrigin("*")
public class PersonController {

    @Autowired
    private PersonService personService;
    private final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    /************************************
     * Get only person by id
     * @param idPerson
     * @return Person
     * **********************************/
    @GetMapping("{id}")
    public ResponseEntity<PersonVo> fetchPersonById(@PathVariable("id") Long idPerson) {

        LOGGER.info("Inside fetchPersonByID PersonController ");
        PersonVo ps = personService.findById(idPerson);
        return ResponseEntity.status(HttpStatus.OK).body(ps);

    }

    /************************************
     * Fetch all persons
     * @return List of Persons
     * **********************************/
    @GetMapping()
    public ResponseEntity<?> fetchAllPerson(){
        LOGGER.info("Inside fetchAllPerson: fetch All Person  ");
        List<PersonVo> personList = personService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(personList);

    }

    /************************************
     * Delete a person by id
     * @param idPerson
     * @return String success
     * **********************************/
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") Long idPerson)  {

        LOGGER.info("Inside delete Person PersonController ");
        personService.delete(idPerson);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /************************************
     * Create a new person
     * @param person
     * @return Person
     * **********************************/
    @PostMapping()
    public ResponseEntity<PersonVo> savePerson(@Validated @RequestBody PersonVo person) {
        LOGGER.info("Inside SavePerson PersonController ");
        PersonVo ps = personService.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(ps);
    }

    /************************************
     * Update person by id
     * @param idPerson
     * @param person
     * @return Person
     * **********************************/
    @PutMapping("{id}")
    public ResponseEntity<PersonVo> updatePerson(@PathVariable("id") Long idPerson, @Valid @RequestBody PersonVo person){
        LOGGER.info("Inside updatePerson PersonController ");
        PersonVo ps = personService.updatePerson(idPerson, person);
        return ResponseEntity.status(HttpStatus.OK).body(ps);
    }
}
