package com.lz.crm.web.controller;

import com.lz.crm.domain.Role;
import com.lz.crm.query.QueryObject;
import com.lz.crm.service.IPermissionService;
import com.lz.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    // 提供一个方法处理查询所有部门数据请求，响应 HTML
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        model.addAttribute("result", roleService.query(qo));
        return "role/list"; // /WEB-INF/views/role/potentialList.ftl
    }

    @RequestMapping("/input")
    public String input(Long id, Model model) {
        if (id != null) { // 点击了修改按钮过来
            Role role = roleService.get(id);
            model.addAttribute("role", role);
        }

        //查询所有权限
        model.addAttribute("permissions", permissionService.listAll());
        return "role/input"; // /WEB-INF/views/role/input.ftl
    }


    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role, Long[] ids) {
        if (role.getId() == null) { // 代表新增
            roleService.save(role, ids);
        } else { // 代表修改
            roleService.update(role, ids);
        }
        return "redirect:/role/list.do";  // http://localhost/role/list.do
    }


    @RequestMapping("/delete")
    public String delete(Long id) {
        if (id != null) {
            roleService.delete(id);
        }
        return "redirect:/role/list.do";  // http://localhost/role/list.do
    }
}
