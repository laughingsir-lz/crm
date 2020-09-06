package com.lz.crm.mapper;

import com.lz.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface CustomerReportMapper {

    List<Map> selectCustomerReport(QueryObject qo);
}
