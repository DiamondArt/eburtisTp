package com.eburtis.tp.infrastructure;

import com.eburtis.tp.domain.department.Department;
import java.util.List;

/*******************************************************************
 * Department implementation
 * @author  Melissa Kouadio
 * @version 1.0
 *****************************************************************/

public interface DepartmentImplementation {

    public Department saveDepartment(Department department);

    List<Department> fetchDepartmentList();

    Department fetchDepartment(Long idDepartment) ;

    void deleteDepartment(Long idDepartment) ;

    Department updateDepartment(Long idDepartment, Department department) ;

}
