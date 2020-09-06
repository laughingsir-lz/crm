package com.lz.crm.mapper;

import com.lz.crm.domain.Customer;
import com.lz.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    Customer selectByPrimaryKey(Long id);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);

    List<Customer> selectForList(QueryObject qo);

    void updateSeller(@Param("customerId") Long customerId, @Param("sellerId") Long sellerId);
}