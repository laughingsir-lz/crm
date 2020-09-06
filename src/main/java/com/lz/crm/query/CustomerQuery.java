package com.lz.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerQuery extends QueryObject {
    private Integer status = -1; //状态 (高级查询有下拉框查询)
    private Long sellerId = -1L;//销售人员id
}
