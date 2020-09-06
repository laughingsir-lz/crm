package com.lz.crm.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 客户跟进历史
 */
@Setter
@Getter
public class CustomerTraceHistory {
    private Long id;
    //跟进时间
    // @DateTimeFormat 把前端传过来的日期字符串自动按照pattern规定的格式解析成date类型的对象
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date traceTime;
    //跟进内容
    private String traceDetails;
    //跟进方式
    private SystemDictionaryItem traceType;
    //跟进结果
    private Integer traceResult;
    //备注
    private String remark;
    //客户
    private Customer customer;
    //录入人
    private Employee inputUser;
    //录入时间
    private Date inputTime;
    //跟进类型
    private Integer type;
}