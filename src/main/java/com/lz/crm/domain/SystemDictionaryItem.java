package com.lz.crm.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * 字典明细
 */
@Getter
@Setter
public class SystemDictionaryItem {
    private Long id;
    //所属目录id
    private Long parentId;
    //标题
    private String title;
    //序号
    private Integer sequence;

    public String getJson() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("sequence", sequence);
        map.put("parentId", parentId);
        return JSON.toJSONString(map);
    }
}