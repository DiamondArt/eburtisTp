package com.eburtis.tp.infrastructure;

import com.eburtis.tp.application.DepartmentService;
import com.eburtis.tp.domain.department.Department;
import com.eburtis.tp.domain.department.DepartmentRepository;
import com.eburtis.tp.domain.department.DepartmentVo;
import com.eburtis.tp.domain.person.Person;
import com.eburtis.tp.domain.person.PersonVo;
import com.eburtis.tp.exceptions.EntityNotFoundException;
import com.eburtis.tp.exceptions.ErrorCodes;
import com.eburtis.tp.exceptions.InvalidEntityException;
import com.eburtis.tp.validator.EntityValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/*******************************************************************
 * Department implementation
 * @author  Melissa Kouadio
 * @version 1.0
 *****************************************************************/

@Service
@Slf4j
public class DepartmentImplementation implements DepartmentService {

    private DepartmentRepository departmentRepository ;

    @Autowired
    public DepartmentImplementation(
            DepartmentRepository departmentRepository1) {
        this.departmentRepository = departmentRepository1;
    }

    /**
     * @param departmentVo
     * @return DepartmentVo
     */
    @Override
    public Department save(Department departmentVo) {
//        List<String> errors = EntityValidator.departmentValidate(departmentVo);
//        if (!errors.isEmpty()) {
//            log.error("Department is not valid {}", departmentVo);
//            throw new InvalidEntityException("Departement n'est pas valide", ErrorCodes.DEPARTMENT_NOT_VALID, errors);
//        }
        return
                departmentRepository.save(departmentVo);

    }
//    public DepartmentVo save(DepartmentVo departmentVo) {
//        List<String> errors = EntityValidator.departmentValidate(departmentVo);
//        if (!errors.isEmpty()) {
//            log.error("Department is not valid {}", departmentVo);
//            throw new InvalidEntityException("Departement n'est pas valide", ErrorCodes.DEPARTMENT_NOT_VALID, errors);
//        }
//        return DepartmentVo.fromEntity(
//                departmentRepository.save(
//                        DepartmentVo.toEntity(departmentVo)
//                )
//        );
//
//    }

    /**
     * @param id
     * @return
     */
    @Override
    public DepartmentVo findById(Long id) {
        if (id == null) {
            log.error("Department ID is null");
            return null;
        }
        return departmentRepository.findById(id).map(DepartmentVo::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune Departement avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.PERSON_NOT_FOUND)
        );
    }

    /**
     * @return
     */
    @Override
    public List<DepartmentVo> findAll() {
        return departmentRepository.findAll().stream()
                .map(DepartmentVo::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * @param idDepartment
     * @param department
     * @return DepartmentVo
     */
    @Override
    public Department updateDepartment(Long idDepartment, DepartmentVo department) {

        Optional<Department> findDepartment = departmentRepository.findById(idDepartment);

        if (!findDepartment.isPresent()) {
            throw new EntityNotFoundException("No value present", ErrorCodes.DEPARTMENT_NOT_FOUND);
        }

        List<String> errors = EntityValidator.departmentValidate(department);
        if (!errors.isEmpty()) {
            log.error("Department is not valid {}", department);
            throw new InvalidEntityException("Le departement  n'est pas valide", ErrorCodes.DEPARTMENT_NOT_VALID, errors);
        }

        Department departmentDt = findDepartment.get();

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
    public void delete(Long idDepartment) {

        Optional<Department> department = departmentRepository.findById(idDepartment);
        if (!department.isPresent()) {
            log.error("Department ID is null");
            throw new EntityNotFoundException("Department not available ", ErrorCodes.DEPARTMENT_NOT_VALID);
        }
        departmentRepository.deleteById(idDepartment);
    }




}
