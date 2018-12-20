package com.codegym.finalexam.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee_group")
public class GroupEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    public GroupEmployee() {
    }

    public GroupEmployee(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
