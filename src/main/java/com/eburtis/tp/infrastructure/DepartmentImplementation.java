package com.eburtis.tp.infrastructure;

import com.eburtis.tp.domain.Department;
import com.eburtis.tp.exceptions.EntityException;

import java.util.List;

public interface DepartmentImplementation {

    public Department saveDepartment(Department department);

    List<Department> fetchDepartmentList();

    Department fetchDepartment(Long idDepartment) throws EntityException;

    void deleteDepartment(Long idDepartment) throws EntityException;

    Department updateDepartment(Long idDepartment, Department department) throws EntityException;

}
