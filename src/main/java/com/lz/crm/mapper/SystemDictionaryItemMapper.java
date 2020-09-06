package com.lz.crm.mapper;

import com.lz.crm.domain.SystemDictionaryItem;
import com.lz.crm.query.QueryObject;

import java.util.List;

public interface SystemDictionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    List<SystemDictionaryItem> selectAll();

    int updateByPrimaryKey(SystemDictionaryItem record);

    List<SystemDictionaryItem> selectForList(QueryObject qo);

    int selectMaxSequenceByParentId(Long id);

    List<SystemDictionaryItem> selectByDicSn(String sn);
}