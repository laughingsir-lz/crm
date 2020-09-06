package com.lz.crm.service.impl;

import com.lz.crm.domain.Role;
import com.lz.crm.mapper.RoleMapper;
import com.lz.crm.query.QueryObject;
import com.lz.crm.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void delete(Long id) {
        roleMapper.deleteByPrimaryKey(id);
        //删除关联
        roleMapper.deleteRelation(id);
    }

    @Override
    public void save(Role role, Long[] ids) {
        roleMapper.insert(role);
        //处理中间表
        if (ids != null && ids.length > 0) {
            for (Long pid : ids) {
                roleMapper.insertRelation(role.getId(), pid);
            }
        }

    }

    @Override
    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.selectAll();
    }

    @Override
    public void update(Role role, Long[] ids) {
        roleMapper.updateByPrimaryKey(role);
        //删除关联
        roleMapper.deleteRelation(role.getId());

        //处理中间表
        if (ids != null && ids.length > 0) {
            for (Long pid : ids) {
                roleMapper.insertRelation(role.getId(), pid);
            }
        }
    }
    @Override
    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Role> list = roleMapper.selectForList(qo);
        return new PageInfo(list);
    }
}
