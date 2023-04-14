package com.eburtis.tp.interfaces;

import com.eburtis.tp.application.DepartmentService;
import com.eburtis.tp.domain.department.Department;
import com.eburtis.tp.domain.department.DepartmentVo;
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
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);


    /**
     * Get department by id
     * @param idDepartment
     * @return Department
     **/
    @GetMapping("{id}")
    public DepartmentVo fetchDepartmentById(@PathVariable("id") Long idDepartment) {

        logger.info("Inside fetchDepartmentByID DepartmentController ");
        DepartmentVo department1 = departmentService.findById(idDepartment);
        return department1;
    }

    /**
     * Get all departments
     * @return List of department
     * */
    @GetMapping()
    public List<DepartmentVo> fetchAllDepartment() {

        logger.info("Inside fetchAllDepartment: fetch All Department  ");
        List<DepartmentVo> departmentList = departmentService.findAll();
        return departmentList;
    }

    /**
     * delete a department by id
     * @param idDepartment
     * @return String
     **/
    @DeleteMapping("{id}")
    public ResponseEntity deleteDepartment(@PathVariable("id") Long idDepartment) {

        logger.info("Inside delete Department DepartmentController ");
        departmentService.delete(idDepartment);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

    }

    /**
     * Create a new department
     * @param department
     * @return Department
     **/
    @PostMapping()
    public ResponseEntity<Department> saveDepartment(@Validated @RequestBody Department department) {

        logger.info("Inside SaveDepartment DepartmentController ");
        Department department1 = departmentService.save(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(department1);

    }

    /**
     * Update a department by id
     * @param idDepartment
     * @param department
     * @return Department
     ***/
    @PutMapping("{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long idDepartment, @Valid @RequestBody DepartmentVo department){

        logger.info("Inside updateDepartment DepartmentController ");
        Department department1 =  departmentService.updateDepartment(idDepartment, department);
        return ResponseEntity.status(HttpStatus.OK).body(department1);
    }
}
