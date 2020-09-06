package com.lz.crm.mapper;

import com.lz.crm.domain.CustomerTransfer;
import com.lz.crm.query.QueryObject;

import java.util.List;

public interface CustomerTransferMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerTransfer record);

    CustomerTransfer selectByPrimaryKey(Long id);

    List<CustomerTransfer> selectAll();

    int updateByPrimaryKey(CustomerTransfer record);

    List<CustomerTransfer> selectForList(QueryObject qo);
}