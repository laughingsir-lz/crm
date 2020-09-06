package com.lz.crm.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * 字典目录
 */
@Setter
@Getter
public class SystemDictionary {
    private Long id;
    //编码
    private String sn;
    //标题
    private String title;
    //简介
    private String intro;

    public String getJson() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("sn", sn);
        map.put("title", title);
        map.put("intro", intro);
        return JSON.toJSONString(map);
    }
}