package com.lz.crm.mapper;

import com.lz.crm.domain.Role;
import com.lz.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> selectForList(QueryObject qo);

    void insertRelation(@Param("rid") Long rid, @Param("pid") Long pid);

    void deleteRelation(Long id);

    List<String> selectSnByEmpId(Long id);
}