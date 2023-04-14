package com.eburtis.tp.application;

import com.eburtis.tp.domain.department.Department;
import com.eburtis.tp.domain.department.DepartmentRepository;
import com.eburtis.tp.domain.department.DepartmentVo;
import com.eburtis.tp.domain.person.Person;
import com.eburtis.tp.domain.person.PersonVo;
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
public interface DepartmentService {

    Department save(Department departmentVo);

    DepartmentVo findById(Long id);

    List<DepartmentVo> findAll();

    Department updateDepartment(Long id, DepartmentVo departmentVo);

    void delete(Long id);
}
