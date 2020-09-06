package com.lz.crm.web.controller;

import com.lz.crm.domain.Department;
import com.lz.crm.query.QueryObject;
import com.lz.crm.service.IDepartmentService;
import com.lz.crm.util.JsonResult;
import com.lz.crm.util.RequiredPermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    // 提供一个方法处理查询所有部门数据请求，响应 HTML
    @RequestMapping("/list")
    //必须要有指定的权限才能访问该方法
    @RequiredPermission(name = "department:list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        PageInfo pageInfo = departmentService.query(qo);
        model.addAttribute("result", pageInfo);
        return "department/list"; // /WEB-INF/views/department/potentialList.ftl
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    @RequiredPermission(name = "department:saveOrUpdate")
    public JsonResult saveOrUpdate(Department department) {
        if (department.getId() == null) { // 代表新增
            departmentService.save(department);
        } else { // 代表修改
            departmentService.update(department);
        }
        return new JsonResult();
    }


    @RequestMapping("/delete")
    @ResponseBody
    @RequiredPermission(name = "department:delete")
    public JsonResult delete(Long id) {
        departmentService.delete(id);
        return new JsonResult();
    }
}
