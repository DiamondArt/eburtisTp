package com.eburtis.tp.domain.department;

import com.eburtis.tp.domain.person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;


/*******************************************************************
 * Department entity
 * @author Melissa Kouadio
 * @version 1.0
 *****************************************************************/
@Entity
@Table(name = "departments")
public class Department {

    public static final String TABLE_NAME = "department";

    public static final String ID = "id";
    public static final String TABLE_ID = ID + '_'+  TABLE_NAME ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "designation", nullable = false)
    private String designation;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Person> persons;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    public Department() {
        super();
    }

    public Department(Long id, String code, String designation) {
        this.id = id;
        this.code = code;
        this.designation = designation;
    }

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

    public List<Person> getPersons() {
        return persons;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
