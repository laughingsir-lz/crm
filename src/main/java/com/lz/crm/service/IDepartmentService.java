package com.lz.crm.service;

import com.lz.crm.domain.Department;
import com.lz.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {
    void delete(Long id);

    void save(Department department);

    Department get(Long id);

    List<Department> listAll();

    void update(Department department);

    /**
     * 分页
     *
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);
}
