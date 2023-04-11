package com.eburtis.tp.application;

import com.eburtis.tp.domain.department.Department;
import com.eburtis.tp.domain.department.DepartmentRepository;
import com.eburtis.tp.infrastructure.DepartmentImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/*******************************************************************
 * Department service
 * @author  Melissa Kouadio
 * @version 1.0
 *****************************************************************/
@Service
public class DepartmentService implements DepartmentImplementation {

    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * @param department
     * @return Department
     */
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    /**
     * @return
     */
    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    /**
     * @param idDepartment
     * @return
     */
    @Override
    public Department fetchDepartment(Long idDepartment)  {
        Optional<Department> department = departmentRepository.findById(idDepartment);
        if (!department.isPresent()) {
            //throw new EntityException("department not found",HttpStatus.NOT_FOUND);
        }
        return department.get();
    }

    /**
     * @param idDepartment
     * @param department
     * @return
     */
    @Override
    public Department updateDepartment(Long idDepartment, Department department)  {
        Optional<Department> findDepartment = departmentRepository.findById(idDepartment);

        if (!findDepartment.isPresent()) {
            // throw new EntityException("No value present", HttpStatus.NOT_FOUND);
        }

        Department departmentDt = departmentRepository.findById(idDepartment).get();
        if(Objects.nonNull(department.getCode()) &&!"".equalsIgnoreCase(department.getCode())){
            departmentDt.setCode(department.getCode());
        }
        if(Objects.nonNull(department.getDesignation()) &&!"".equalsIgnoreCase(department.getDesignation())){
            departmentDt.setDesignation(department.getDesignation());
        }

        return departmentRepository.save(departmentDt);
    }

    /**
     * @param idDepartment
     */
    @Override
    public void deleteDepartment(Long idDepartment) {
        Optional<Department> department = departmentRepository.findById(idDepartment);
        if (!department.isPresent()) {
            //throw new EntityException("department not available ", HttpStatus.NOT_FOUND);
        }
        departmentRepository.deleteById(idDepartment);
    }
}
