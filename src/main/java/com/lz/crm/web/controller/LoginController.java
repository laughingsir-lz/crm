package com.lz.crm.web.controller;

import com.lz.crm.domain.Employee;
import com.lz.crm.service.IEmployeeService;
import com.lz.crm.service.IPermissionService;
import com.lz.crm.util.JsonResult;
import com.lz.crm.util.MyAdvice;
import com.lz.crm.util.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@Slf4j
@Controller
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/test")
    public String test() {
        return "test";
    }


    @RequestMapping("/loginUser")
    @ResponseBody
    public JsonResult login(String username, String password, HttpSession session, HttpServletRequest request) {
        try {
            Employee employee = employeeService.login(username, password);
            log.info("{}登录了",username);
            log.info("用户身份为{}",employee.isAdmin()?"管理员":"普通用户");
            MyAdvice.testJrebel();
            System.out.println(MyAdvice.getIpAddr(request));
            //把登录用户存到session中
            UserContext.setCurrentUser(employee);
            //如果不是超管,就要把用户的权限数据存到session中
            if (!employee.isAdmin()) {
                List<String> permissions = permissionService.selectByEmpId(employee.getId());
                UserContext.setPermissions(permissions);
            }
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "账号或密码错误");
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.html";
    }
}
