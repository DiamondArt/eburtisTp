package com.eburtis.tp.interfaces;

import com.eburtis.tp.domain.DepartmentRepository;
import com.eburtis.tp.domain.PersonRepository;
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
@Tag("DepartmentControllerTest")
@DisplayName("Testing department controller")
public class DepartmentControllerTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private MockMvc mockMvc;
    private JSONObject jsonObject;
    private String endpoint = "/v1/api/rest";

    // @BeforeAll
    // @AfterAll
    // public void cleanDatabase(){
    // this.departmentRepository.deleteAll();
    // jsonObject = null;
    //    }

    /***********************************
     * Create a department Test
     ***********************************/
    @Test
    @Order(value = 1)
    @DisplayName("Create a department")
    public void TestCanCreateDepartment() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(this.endpoint+"/department").content(
                        "{\n" +
                                "\t\n" +
                                "\t\"code\": \"FT52399\",\n" +
                                "\t\"designation\": \"Departement juridique\"\n" +
                                "}"
                ).contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code", is("FT52399")))
                .andExpect(jsonPath("$.designation", is("Departement juridique")))
                .andReturn();

        jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
    }

    /***********************************
     * Fetch only department with id Test
     ***********************************/
    @Test
    @Order(value = 2)
    @DisplayName("Fetch a department with id")
    public void TestCanReadDepartment() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(this.endpoint+"/department/"+jsonObject.getInt("id")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(jsonObject.getInt("id"))))
                .andExpect(jsonPath("$.code", is("FT52399")))
                .andExpect(jsonPath("$.designation", is("Departement juridique")));
    }


    /***********************************
     * Fetch all departments list Test
     ***********************************/
    @Test
    @Order(value = 3)
    @DisplayName("Fetch department list")
    public void TestCanFetchAllDepartment() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(this.endpoint+"/department"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].code", is("TY859663")))
                .andExpect(jsonPath("$.[0].designation", is("Departement informatique")));
    }


    /*************************************************
     * Update a department information with id Test
     *************************************************/
    @Test
    @Order(value = 4)
    @DisplayName("Update a department with id")
    public void TestCanUpdateDepartment() throws Exception {

                MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.put(this.endpoint+"/department/"+jsonObject.getInt("id")).content(
                        "{\n" +
                                "\t\n" +
                                "\t\"code\": \"SQ258963\",\n" +
                                "\t\"designation\": \"Departement juridique\"\n" +
                                "}"
                ).contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(jsonObject.getInt("id"))))
                .andExpect(jsonPath("$.code", is("SQ258963")))
                .andExpect(jsonPath("$.designation", is("Departement juridique")))
                .andReturn();
        jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
    }


    /*************************************************
     * Delete a department with id Test
     *************************************************/
    @Test
    @Order(value = 5)
    @DisplayName("Delete a department")
    public void TestCanDeletePerson() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.delete(this.endpoint+"/department/"+jsonObject.getInt("id")))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    /*************************************************
     * Cannot find department Test
     *************************************************/
    @Test
    @Order(value = 6)
    @DisplayName("Cannot find department: id is not available")
    public void TestCannotFindDepartment() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(this.endpoint+"/department/"+jsonObject.getInt("id")))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.reasons[0]", is("department not found")));
    }
}
