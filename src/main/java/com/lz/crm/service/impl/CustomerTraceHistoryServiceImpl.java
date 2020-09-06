package com.lz.crm.service.impl;

import com.lz.crm.domain.CustomerTraceHistory;
import com.lz.crm.mapper.CustomerTraceHistoryMapper;
import com.lz.crm.query.QueryObject;
import com.lz.crm.service.ICustomerTraceHistoryService;
import com.lz.crm.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerTraceHistoryServiceImpl implements ICustomerTraceHistoryService {

    @Autowired
    private CustomerTraceHistoryMapper customerTraceHistoryMapper;

    @Override
    public void delete(Long id) {
        customerTraceHistoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(CustomerTraceHistory customerTraceHistory) {
        //设置录入人
        customerTraceHistory.setInputUser(UserContext.getCurrentUser());
        //设置录入时间
        customerTraceHistory.setInputTime(new Date());
        customerTraceHistoryMapper.insert(customerTraceHistory);
    }

    @Override
    public CustomerTraceHistory get(Long id) {
        return customerTraceHistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CustomerTraceHistory> listAll() {
        return customerTraceHistoryMapper.selectAll();
    }

    @Override
    public void update(CustomerTraceHistory customerTraceHistory) {
        customerTraceHistoryMapper.updateByPrimaryKey(customerTraceHistory);
    }

    public PageInfo query(QueryObject qo) {
        //使用分页插件,传入当前页,每页显示数量
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        //查询结果集
        List<CustomerTraceHistory> list = customerTraceHistoryMapper.selectForList(qo);
        return new PageInfo(list);
    }
}
