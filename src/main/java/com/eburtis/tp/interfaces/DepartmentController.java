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

@RestController
@RequestMapping("/v1/api/rest/department")
@CrossOrigin("*")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @GetMapping("{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long idDepartment) throws EntityException {

        LOGGER.info("Inside fetchDepartmentByID DepartmentController ");
        return departmentService.fetchDepartment(idDepartment);
    }

    @GetMapping()
    public List<Department> fetchAllDepartment(){
        LOGGER.info("Inside fetchAllDepartment: fetch All Department  ");
        return departmentService.fetchDepartmentList();
    }

    @DeleteMapping("{id}")
    public String deleteDepartment(@PathVariable("id") Long idDepartment) throws EntityException {

        LOGGER.info("Inside delete Department DepartmentController ");
        departmentService.deleteDepartment(idDepartment);
        return "Success";
    }

    @PostMapping()
    public Department saveDepartment(@Validated @RequestBody Department department) {
        LOGGER.info("Inside SaveDepartment DepartmentController ");
        return departmentService.saveDepartment(department);
    }

    @PutMapping("{id}")
    public Department updateDepartment(@PathVariable("id") Long idDepartment, @Valid @RequestBody Department department) throws EntityException{
        LOGGER.info("Inside updateDepartment DepartmentController ");
        return  departmentService.updateDepartment(idDepartment, department);
    }
}
