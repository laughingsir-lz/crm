package com.lz.crm;

import com.lz.crm.domain.Department;
import com.lz.crm.service.IDepartmentService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CrmApplicationTests {
    @Autowired
    private IDepartmentService departmentService;

    @Test
    void contextLoads() {
        Department department = new Department();
        department.setName("财务部");
        department.setSn("Mo");
        departmentService.save(department);
    }
}
