package com.lz.crm.mapper;

import com.lz.crm.domain.CustomerTraceHistory;
import com.lz.crm.query.QueryObject;

import java.util.List;

public interface CustomerTraceHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerTraceHistory record);

    CustomerTraceHistory selectByPrimaryKey(Long id);

    List<CustomerTraceHistory> selectAll();

    int updateByPrimaryKey(CustomerTraceHistory record);

    List<CustomerTraceHistory> selectForList(QueryObject qo);
}