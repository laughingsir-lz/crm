package com.lz.crm.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class CustomerReportQuery extends QueryObject {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;//开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;//结束时间
    private String groupType = "e.name";//默认按照员工姓名分组
}
