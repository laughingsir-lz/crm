package com.lz.crm.mapper;

import com.lz.crm.domain.Department;
import com.lz.crm.query.QueryObject;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    //    int selectForCount(QueryObject qo);
    List<Department> selectForList(QueryObject qo);
}