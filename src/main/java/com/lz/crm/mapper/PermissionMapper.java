package com.lz.crm.mapper;

import com.lz.crm.domain.Permission;
import com.lz.crm.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> selectForList(QueryObject qo);

    List<String> selectAllExpression();

    List<String> selectByEmpId(Long id);
}