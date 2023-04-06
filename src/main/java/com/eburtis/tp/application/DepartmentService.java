package com.eburtis.tp.application;

import com.eburtis.tp.domain.Department;
import com.eburtis.tp.domain.DepartmentRepository;
import com.eburtis.tp.exceptions.EntityException;
import com.eburtis.tp.infrastructure.DepartmentImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
     * @throws EntityException
     */
    @Override
    public Department fetchDepartment(Long idDepartment) throws EntityException {
        Optional<Department> department = departmentRepository.findById(idDepartment);
        if (!department.isPresent()) {
            throw new EntityException("department not found");
        }
        return department.get();
    }

    /**
     * @param idDepartment
     * @param department
     * @return
     * @throws EntityException
     */
    @Override
    public Department updateDepartment(Long idDepartment, Department department) throws EntityException {
        Optional<Department> findDepartment = departmentRepository.findById(idDepartment);

        if (!findDepartment.isPresent()) {
            throw new EntityException("No value present");
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
     * @throws EntityException
     */
    @Override
    public void deleteDepartment(Long idDepartment) throws EntityException {
        Optional<Department> department = departmentRepository.findById(idDepartment);
        if (!department.isPresent()) {
            throw new EntityException("department not available ");
        }
        departmentRepository.deleteById(idDepartment);
    }
}
