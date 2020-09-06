package com.lz.crm.service.impl;

import com.lz.crm.domain.Department;
import com.lz.crm.mapper.DepartmentMapper;
import com.lz.crm.query.QueryObject;
import com.lz.crm.service.IDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.crm.util.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
public class DepartmentServiceImpl implements IDepartmentService {


    //private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void delete(Long id) {

        departmentMapper.deleteByPrimaryKey(id);
        //记录日志
        log.debug("{}删除了id为{}的部门",UserContext.getCurrentUser().getName(),id);
    }

    @Override
    @Transactional
    public void save(Department department) {
        departmentMapper.insert(department);
       // int i=1/0;
    }

    @Override
    public Department get(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> listAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public void update(Department department) {
        departmentMapper.updateByPrimaryKey(department);
    }

    @Override
    public PageInfo query(QueryObject qo) {
        //使用分页插件,传入当前页,每页显示数量
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        //查询结果集
        List<Department> list = departmentMapper.selectForList(qo);
        return new PageInfo(list);
    }
}
