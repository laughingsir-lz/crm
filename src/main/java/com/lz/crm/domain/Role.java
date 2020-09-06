package com.lz.crm.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 */
@Setter
@Getter
public class Role {
    private Long id;

    //角色名称
    private String name;
    //角色编码
    private String sn;
    //权限集合
    private List<Permission> permissions = new ArrayList<>();
}