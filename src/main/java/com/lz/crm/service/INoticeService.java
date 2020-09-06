package com.lz.crm.service;


import com.github.pagehelper.PageInfo;
import com.lz.crm.domain.Notice;
import com.lz.crm.query.QueryObject;

import java.util.List;

public interface INoticeService {
    void save(Notice notice);
    void delete(Long id);
    void update(Notice notice);
    Notice get(Long id);
    List<Notice> listAll();
    // 分页查询的方法
    PageInfo<Notice> query(QueryObject qo);

    void saveRead(Long noticeId, Long empId);
}
