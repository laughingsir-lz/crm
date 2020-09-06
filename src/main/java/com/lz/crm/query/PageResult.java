package com.lz.crm.query;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

//封装页面中需要的数据(分页结果对象)
@Getter
public class PageResult {

    private Integer currentPage; //当前页
    private Integer pageSize;  //每页显示条数
    //sql查询出来的数据
    private List<?> list; //结果集
    private Integer totalCount; //结果总数
    //计算出来的数据
    private Integer prevPage;  //上一页
    private Integer nextPage;  //下一页
    private Integer totalPage;  //总页数

    //当总条数为0时使用 new PageResult(3)
    public PageResult(Integer pageSize) {
        this(1, 0, Collections.EMPTY_LIST, pageSize);
    }

    public PageResult(Integer currentPage, Integer totalCount, List<?> list, Integer pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
        this.totalCount = totalCount;

        //计算
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        this.prevPage = currentPage > 1 ? currentPage - 1 : 1;
        this.nextPage = currentPage < totalPage ? currentPage + 1 : totalPage;
    }

}