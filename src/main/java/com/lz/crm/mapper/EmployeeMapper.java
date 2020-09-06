package com.lz.crm.mapper;

import com.lz.crm.domain.Employee;
import com.lz.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    List<Employee> selectForList(QueryObject qo);

    void insertRelation(@Param("eid") Long eid, @Param("rid") Long rid);

    void deleteRelation(Long id);

    void deleteBatch(Long[] ids);

    Employee selectByName(String name);

    List<Employee> selectByRoleSn(String... sns);
}