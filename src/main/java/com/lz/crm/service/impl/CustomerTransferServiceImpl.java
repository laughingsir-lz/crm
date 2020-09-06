package com.lz.crm.service.impl;

import com.lz.crm.domain.CustomerTransfer;
import com.lz.crm.mapper.CustomerMapper;
import com.lz.crm.mapper.CustomerTransferMapper;
import com.lz.crm.query.QueryObject;
import com.lz.crm.service.ICustomerTrasferService;
import com.lz.crm.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class CustomerTransferServiceImpl implements ICustomerTrasferService {

    @Autowired
    private CustomerTransferMapper customerTransferMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void save(CustomerTransfer customerTransfer) {
        //把客户的销售人员字段改为新的销售人员
        Long customerId = customerTransfer.getCustomer().getId();
        Long sellerId = customerTransfer.getNewseller().getId();
        customerMapper.updateSeller(customerId, sellerId);

        //--------------保存移交历史记录-------------------
        //设置操作人
        customerTransfer.setOperator(UserContext.getCurrentUser());
        //设置操作时间
        customerTransfer.setOperateTime(new Date());
        customerTransferMapper.insert(customerTransfer);
    }

    @Override
    public CustomerTransfer get(Long id) {
        return customerTransferMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CustomerTransfer> listAll() {
        return customerTransferMapper.selectAll();
    }

    @Override
    public void update(CustomerTransfer customerTransfer) {
        customerTransferMapper.updateByPrimaryKey(customerTransfer);
    }

    public PageInfo query(QueryObject qo) {
        //使用分页插件,传入当前页,每页显示数量
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        //查询结果集
        List<CustomerTransfer> list = customerTransferMapper.selectForList(qo);
        return new PageInfo(list);
    }
}
