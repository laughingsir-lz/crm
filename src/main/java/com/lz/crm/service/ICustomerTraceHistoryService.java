package com.lz.crm.service;

import com.lz.crm.domain.CustomerTraceHistory;
import com.lz.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICustomerTraceHistoryService {
    void delete(Long id);

    void save(CustomerTraceHistory customerTraceHistory);

    CustomerTraceHistory get(Long id);

    List<CustomerTraceHistory> listAll();

    void update(CustomerTraceHistory customerTraceHistory);

    /**
     * 分页
     *
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);
}
