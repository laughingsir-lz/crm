package com.lz.crm.service;

import com.lz.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ICustomerReportService {

    PageInfo selectCustomerReport(QueryObject qo);

    List<Map> listAll(QueryObject qo);
}
