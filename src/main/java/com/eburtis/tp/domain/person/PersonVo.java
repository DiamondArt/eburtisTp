package com.eburtis.tp.domain.person;
import com.eburtis.tp.domain.department.Department;
import lombok.Builder;

import java.util.Objects;
@Builder
public final  class PersonVo {
    private Long id;
    private String firstname;
    private String lastname;
    private int age;
    private Department department;


    // TODO utiliser un constructeur pour PersonVO de cette manière
//    public PersonVo(Person person){
//        this.id = person.getId();
//    }
    public PersonVo(Long id, String firstName, String lastName, int age, Department department1) {
        this.id = id;
        this.firstname = firstName;
        this.lastname = lastName;
        this.age = age;
        this.department = department1;

    }
    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public static PersonVo fromEntity(Person person) {
        if (person == null) {
            return null;
        }
        return PersonVo.builder()
                .id(person.getId())
                .firstname(person.getFirstName())
                .lastname(person.getLastName())
                .age(person.getAge())
                .department(person.getDepartment())
                .build();
    }

    public static Person toEntity(PersonVo personVo) {
        if (personVo == null) {
            return null;
        }
        Person person = new Person();
        person.setId(person.getId());
        person.setFirstName(personVo.getFirstname());
        person.setLastName(personVo.getLastname());
        person.setAge(personVo.getAge());
        person.setDepartment(personVo.getDepartment());
        return person;
    }

}
