package com.eburtis.tp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*******************************************************************
 * Department repository
 * @author  Melissa Kouadio
 * @version 1.0
 *****************************************************************/
@Repository
public interface DepartmentRepository extends  JpaRepository<Department,Long> {
}
