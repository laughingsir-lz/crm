package com.lz.crm.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class QueryObject {

    private int currentPage = 1; //当前页
    private int pageSize = 3; //每页显示条数

    private String keyword; //关键字

    public String getKeyword() {
        return StringUtils.hasLength(keyword) ? keyword.trim() : null;
    }
		
	/*//计算开始的索引
	public int getStart(){
		return (currentPage - 1) * pageSize;
	}*/
   private Long currentUserId;
}