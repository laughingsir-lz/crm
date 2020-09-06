package com.lz.crm.service.impl;

import com.lz.crm.domain.SystemDictionary;
import com.lz.crm.mapper.SystemDictionaryMapper;
import com.lz.crm.query.QueryObject;
import com.lz.crm.service.ISystemDictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {

    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;

    @Override
    public void delete(Long id) {
        systemDictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(SystemDictionary systemDictionary) {
        systemDictionaryMapper.insert(systemDictionary);
    }

    @Override
    public SystemDictionary get(Long id) {
        return systemDictionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemDictionary> listAll() {
        return systemDictionaryMapper.selectAll();
    }

    @Override
    public void update(SystemDictionary systemDictionary) {
        systemDictionaryMapper.updateByPrimaryKey(systemDictionary);
    }

    public PageInfo query(QueryObject qo) {
        //使用分页插件,传入当前页,每页显示数量
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        //查询结果集
        List<SystemDictionary> list = systemDictionaryMapper.selectForList(qo);
        return new PageInfo(list);
    }
}
