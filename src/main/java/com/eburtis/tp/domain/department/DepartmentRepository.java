package com.eburtis.tp.domain.department;

import com.eburtis.tp.domain.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/*******************************************************************
 * Department repository
 * @author  Melissa Kouadio
 * @version 1.0
 *****************************************************************/
@Repository
public interface DepartmentRepository extends  JpaRepository<Department,Long> {
}
