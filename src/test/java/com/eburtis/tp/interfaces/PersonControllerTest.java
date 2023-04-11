package com.eburtis.tp.interfaces;

import com.eburtis.tp.domain.person.PersonRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/************************************************************
 * @author Meissa kouadio <https://github.com/DiamondArt>
 * *********************************************************/

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@Tag("PersonControllerTest")
@DisplayName("Testing person controller")
public class PersonControllerTest {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MockMvc mockMvc;
    private JSONObject jsonObject;
    private String endpoint = "/v1/api/rest";

    // @BeforeAll
    // @AfterAll
    // public void cleanDatabase(){
    // this.personRepository.deleteAll();
    // jsonObject = null;
    //    }

   /***********************************
    * Create a person Test
    ***********************************/
    @Test
    @Order(value = 1)
    @DisplayName("Create a person")
    public void TestCanCreatePerson() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(this.endpoint+"/person").content(
                        "{\n" +
                                "\t\"firstname\" : \"Carla\",\n" +
                                "\t\"lastname\": \"Parker\",\n" +
                                "\t\"age\": 25,\n" +
                                "\t\"department\": {\"id\":1}\n" +
                                "}"
                ).contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", is("Carla")))
                .andExpect(jsonPath("$.lastname", is("Parker")))
                .andExpect(jsonPath("$.age", is(25)))
                .andExpect(jsonPath("$.department.id", is(1)))
                .andReturn();

        jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
    }


    /***********************************
     * Fetch only person with id Test
     ***********************************/
    @Test
    @Order(value = 2)
    @DisplayName("Fetch a person")
    public void TestCanReadPerson() throws Exception {

      this.mockMvc.perform(
                 MockMvcRequestBuilders.get(this.endpoint+"/person/"+jsonObject.getInt("id")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(jsonObject.getInt("id"))))
                .andExpect(jsonPath("$.firstname", is("Carla")))
                .andExpect(jsonPath("$.lastname", is("Parker")))
                .andExpect(jsonPath("$.age", is(25)))
                .andExpect(jsonPath("$.department.id", is(1)));
    }


    /***********************************
     * Fetch all persons Test
     ***********************************/
    @Test
    @Order(value = 3)
    @DisplayName("Fetch person list")
    public void TestCanFetchAllPerson() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(this.endpoint+"/person"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].firstname", is("Melissa")))
                .andExpect(jsonPath("$.[0].lastname", is("Kouadio")))
                .andExpect(jsonPath("$.[0].age", is(56)))
                .andExpect(jsonPath("$.[0].department.id", is(1)));
    }


    /*************************************************
     * Update a person information with id Test
     *************************************************/
    @Test
    @Order(value = 4)
    @DisplayName("Update a person")
    public void TestCanUpdatePerson() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.put(this.endpoint+"/person/"+jsonObject.getInt("id")).content(
                        "{\n" +
                                "\t\"firstname\" : \"Melissa\",\n" +
                                "\t\"lastname\": \"Parker\",\n" +
                                "\t\"age\": 25,\n" +
                                "\t\"department\": {\"id\":1}\n" +
                                "}"
                ).contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(jsonObject.getInt("id"))))
                .andExpect(jsonPath("$.firstname", is("Melissa")))
                .andExpect(jsonPath("$.lastname", is("Parker")))
                .andExpect(jsonPath("$.age", is(25)))
                .andExpect(jsonPath("$.department.id", is(1)))
                .andReturn();

        jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
    }


    /*************************************************
     * Delete a person with id Test
     *************************************************/
    @Test
    @Order(value = 5)
    @DisplayName("Delete a person")
    public void TestCanDeletePerson() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.delete(this.endpoint+"/person/"+jsonObject.getInt("id")))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    /*************************************************
     * Cannot find person Test
     *************************************************/
    @Test
    @Order(value = 6)
    @DisplayName("Cannot find person: id is not available")
    public void TestCannotFindPerson() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(this.endpoint+"/person/"+jsonObject.getInt("id")))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("PERSON_NOT_FOUND")));
    }
}
