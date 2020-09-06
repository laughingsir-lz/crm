package com.lz.crm.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Employee {
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String email;
    private boolean admin; // true false null
    private Department dept;
    private List<Role> roles = new ArrayList<>();

}
