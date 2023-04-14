package com.eburtis.tp.domain.department;

import lombok.Builder;

import java.util.Objects;
@Builder
public final  class DepartmentVo {
    private Long id;
    private String code;
    private String designation;

    public DepartmentVo(Long id, String code, String designation) {
        this.id = id;
        this.code = code;
        this.designation = designation;

    }
    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public static DepartmentVo fromEntity(Department department) {
        if (department == null) {
            return null;
        }
        return DepartmentVo.builder()
                .id(department.getId())
                .code(department.getCode())
                .designation(department.getDesignation())
                .build();
    }

    public static Department toEntity(DepartmentVo departmentVo) {
        if (departmentVo == null) {
            return null;
        }
        Department department = new Department();
        department.setId(department.getId());
        department.setCode(department.getCode());
        department.setDesignation(department.getDesignation());
        return department;
    }

}
