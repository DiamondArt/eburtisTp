package com.eburtis.tp.interfaces;

import com.eburtis.tp.application.DepartmentService;
import com.eburtis.tp.domain.Department;
import com.eburtis.tp.exceptions.EntityException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*******************************************************************
 * Department controller
 * @author  Melissa Kouadio
 * @version 1.0
 *****************************************************************/
@RestController
@RequestMapping("/v1/api/rest/department")
@CrossOrigin("*")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);


    /************************************
     * Get department by id
     * @param idDepartment
     * @return Department
     * **********************************/
    @GetMapping("{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long idDepartment) throws EntityException {

        LOGGER.info("Inside fetchDepartmentByID DepartmentController ");
        return departmentService.fetchDepartment(idDepartment);
    }

    /************************************
     * Get all departments
     * @return List of department
     * **********************************/
    @GetMapping()
    public List<Department> fetchAllDepartment(){
        LOGGER.info("Inside fetchAllDepartment: fetch All Department  ");
        return departmentService.fetchDepartmentList();
    }

    /************************************
     * delete a department by id
     * @param idDepartment
     * @return String
     * **********************************/
    @DeleteMapping("{id}")
    public String deleteDepartment(@PathVariable("id") Long idDepartment) throws EntityException {

        LOGGER.info("Inside delete Department DepartmentController ");
        departmentService.deleteDepartment(idDepartment);
        return "Success";
    }

    /************************************
     * Create a new department
     * @param department
     * @return Department
     * **********************************/
    @PostMapping()
    public Department saveDepartment(@Validated @RequestBody Department department) {
        LOGGER.info("Inside SaveDepartment DepartmentController ");
        return departmentService.saveDepartment(department);
    }

    /************************************
     * Update a department by id
     * @param idDepartment
     * @param department
     * @return Department
     * **********************************/
    @PutMapping("{id}")
    public Department updateDepartment(@PathVariable("id") Long idDepartment, @Valid @RequestBody Department department) throws EntityException{
        LOGGER.info("Inside updateDepartment DepartmentController ");
        return  departmentService.updateDepartment(idDepartment, department);
    }
}
