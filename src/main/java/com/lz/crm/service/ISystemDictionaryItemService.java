package com.lz.crm.service;

import com.lz.crm.domain.SystemDictionaryItem;
import com.lz.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISystemDictionaryItemService {
    void delete(Long id);

    void save(SystemDictionaryItem systemDictionaryItem);

    SystemDictionaryItem get(Long id);

    List<SystemDictionaryItem> listAll();

    void update(SystemDictionaryItem systemDictionaryItem);

    /**
     * 分页
     *
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);

    /**
     * 根据目录编码查询明细
     *
     * @param sn
     * @return
     */
    List<SystemDictionaryItem> selectByDicSn(String sn);
}
