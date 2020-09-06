package com.lz.crm.service;

import com.lz.crm.domain.CustomerTransfer;
import com.lz.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICustomerTrasferService {
    void save(CustomerTransfer customerTransfer);

    CustomerTransfer get(Long id);

    List<CustomerTransfer> listAll();

    void update(CustomerTransfer customerTransfer);

    /**
     * 分页
     *
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);
}
