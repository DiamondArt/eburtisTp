package com.eburtis.tp.validator;

import com.eburtis.tp.domain.department.DepartmentVo;
import com.eburtis.tp.domain.person.PersonVo;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntityValidator {

    public static List<String> personValidate(PersonVo vo) {
        List<String> errors = new ArrayList<>();

        if (vo == null) {
            errors.add("Veuillez renseigner le nom");
            errors.add("Veuillez renseigner le prénoms");
            errors.add("Veuillez renseigner l'age");
            errors.add("Veuillez renseigner le departement");
            return errors;
        }

        if (!StringUtils.hasLength(vo.getFirstname())) {
            errors.add("Veuillez renseigner le prénom(s)");
        }
        if (!StringUtils.hasLength(vo.getLastname())) {
            errors.add("Veuillez renseigner le nom");
        }

//        if (vo.getAge() == Integer.parseInt(null)) {
//            errors.add("Veuillez renseigner l'age");
//        }

        if (vo.getDepartment() == null || vo.getDepartment().getId() == null) {
            errors.add("Veuillez selectionner un departement");
        }
        return errors;
    }


    public static List<String> departmentValidate(DepartmentVo vo) {
        List<String> errors = new ArrayList<>();

        if (vo == null) {
            errors.add("Veuillez renseigner le code");
            errors.add("Veuillez renseigner la designation");

            return errors;
        }

        if (!StringUtils.hasLength(vo.getCode())) {
            errors.add("Veuillez renseigner le code");
        }
        if (!StringUtils.hasLength(vo.getDesignation())) {
            errors.add("Veuillez renseigner la designation");
        }
        return errors;
    }

}

