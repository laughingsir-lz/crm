package com.lz.crm.web.controller;

import com.lz.crm.domain.Employee;
import com.lz.crm.query.EmployeeQuery;
import com.lz.crm.service.IDepartmentService;
import com.lz.crm.service.IEmployeeService;
import com.lz.crm.service.IRoleService;
import com.lz.crm.util.JsonResult;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") EmployeeQuery qo) {
        // Subject subject = SecurityUtils.getSubject();
        // boolean admin = subject.hasRole("admin");
        //System.out.println("判断用户是否有admin角色:"+admin);
        //System.out.println("判断用户是否有hr角色:"+subject.hasRole("hr"));
        //查询全部部门
        model.addAttribute("depts", departmentService.listAll());
        model.addAttribute("result", employeeService.query(qo));
        return "employee/list";
    }

    @RequestMapping("/input")
    public String input(Model model, Long id) {
        if (id != null) { //编辑
            Employee employee = employeeService.get(id);
            model.addAttribute("employee", employee);
        }
        //查询全部部门
        model.addAttribute("depts", departmentService.listAll());
        //查询全部角色
        model.addAttribute("roles", roleService.listAll());

        return "employee/input";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Employee employee, Long[] ids) {
        if (employee.getId() == null) {
            employeeService.save(employee, ids);
        } else {
            employeeService.update(employee, ids);
        }
        return new JsonResult();
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public JsonResult batchDelete(Long[] ids) {
        employeeService.batchDelete(ids);
        return new JsonResult();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        employeeService.delete(id);
        return new JsonResult();
    }

    /**
     * 用户远程验证用户名是否存在
     * 返回Map   true代表验证通过(该用户名不存在) false代表验证不通过(用户名已经存在)
     *
     * @param name
     * @return
     */
    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String, Boolean> checkName(String name) {  //插件要求 valid:true
        Employee employee = employeeService.selectByName(name);
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("valid", employee == null);
        return map;
    }

    @RequestMapping("/exportXls")
    public void exportXls(HttpServletResponse response) throws Exception {
        //文件下载的响应头
        response.setHeader("Content-Disposition", "attachment;filename=employee.xls");
        //直接使用poi
        //创建excel文件
        Workbook wb = employeeService.exportXls();
        //把excel的数据输出给浏览器
        wb.write(response.getOutputStream());
    }

    @RequestMapping("/importXls")
    @ResponseBody
    public JsonResult importXls(MultipartFile file) throws Exception {
        employeeService.importXls(file);
        return new JsonResult();
    }
}
