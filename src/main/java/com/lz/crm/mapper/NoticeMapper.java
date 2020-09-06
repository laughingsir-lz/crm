package com.lz.crm.mapper;


import com.lz.crm.domain.Notice;
import com.lz.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Notice record);

    Notice selectByPrimaryKey(Long id);

    List<Notice> selectAll();

    int updateByPrimaryKey(Notice record);

    List<Notice> selectForList(QueryObject qo);

    void saveRead(@Param("noticeId") Long noticeId,@Param("empId") Long empId);

    int getRead(@Param("noticeId") Long noticeId,@Param("empId") Long empId);

    void deleteRead(Long id);
}