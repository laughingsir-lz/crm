package com.lz.crm.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;

@Setter
@Getter
@ToString
public class Notice {
    private Long id;
    private Employee issuer;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date pubdate;
    private String title;
    private String content;
    private int read;
    public String getJson() {
        HashMap map = new HashMap();
        map.put("id", id);
        map.put("issuer", issuer);
        map.put("pubdate", pubdate);
        map.put("title", title);
        map.put("content", content);
        map.put("read", read);
        return JSON.toJSONString(map);
    }

}