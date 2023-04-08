package com.eburtis.tp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;


/*******************************************************************
 * Department entity
 * @author  Melissa Kouadio
 * @version 1.0
 *****************************************************************/
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "the field code is required")
    @NotBlank(message = "the field code cannot blank")
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull(message = "the field designation is required")
    @NotBlank(message = "the field designation cannot blank")
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

    public Department(){
        super();
    }
    public Department(Long id, String code, String designation, List<Person> persons) {
        this.id = id;
        this.code = code;
        this.designation = designation;
        this.persons = persons;
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
