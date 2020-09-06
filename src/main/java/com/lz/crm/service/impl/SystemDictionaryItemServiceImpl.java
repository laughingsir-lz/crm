package com.lz.crm.service.impl;

import com.lz.crm.domain.SystemDictionaryItem;
import com.lz.crm.mapper.SystemDictionaryItemMapper;
import com.lz.crm.query.QueryObject;
import com.lz.crm.service.ISystemDictionaryItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryItemServiceImpl implements ISystemDictionaryItemService {

    @Autowired
    private SystemDictionaryItemMapper systemDictionaryItemMapper;

    @Override
    public void delete(Long id) {
        systemDictionaryItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(SystemDictionaryItem systemDictionaryItem) {
        //判断用户是否有填序号,如果没有填,就自动设置为当前目录下的最大的明细序号
        if (systemDictionaryItem.getSequence() == null) {
            int max = systemDictionaryItemMapper.selectMaxSequenceByParentId(systemDictionaryItem.getParentId());
            systemDictionaryItem.setSequence(max + 1);
        }

        systemDictionaryItemMapper.insert(systemDictionaryItem);
    }

    @Override
    public SystemDictionaryItem get(Long id) {
        return systemDictionaryItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemDictionaryItem> listAll() {
        return systemDictionaryItemMapper.selectAll();
    }

    @Override
    public void update(SystemDictionaryItem systemDictionaryItem) {
        systemDictionaryItemMapper.updateByPrimaryKey(systemDictionaryItem);
    }

    public PageInfo query(QueryObject qo) {
        //使用分页插件,传入当前页,每页显示数量,排序规则
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), "sequence asc");
        //查询结果集
        List<SystemDictionaryItem> list = systemDictionaryItemMapper.selectForList(qo);
        return new PageInfo(list);
    }

    public List<SystemDictionaryItem> selectByDicSn(String sn) {
        return systemDictionaryItemMapper.selectByDicSn(sn);
    }
}
