package com.lz.crm.web.controller;

import com.lz.crm.query.QueryObject;
import com.lz.crm.service.IPermissionService;
import com.lz.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    // 提供一个方法处理查询所有部门数据请求，响应 HTML
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        model.addAttribute("result", permissionService.query(qo));
        return "permission/list"; // /WEB-INF/views/permission/potentialList.ftl
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        if (id != null) {
            permissionService.delete(id);
        }
        return "redirect:/permission/list.do";  // http://localhost/permission/list.do
    }

    @RequestMapping("/reload")
    @ResponseBody //把返回的对象转为json格式的数据相应给前端 (前端发送ajax请求)
    public JsonResult reload() {
        try {
            permissionService.reload();
            return new JsonResult(true, "加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "加载失败");
        }
    }

}
