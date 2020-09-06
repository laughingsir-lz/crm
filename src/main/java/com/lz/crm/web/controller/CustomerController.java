package com.lz.crm.web.controller;

import com.lz.crm.domain.Customer;
import com.lz.crm.domain.Employee;
import com.lz.crm.domain.SystemDictionaryItem;
import com.lz.crm.query.CustomerQuery;
import com.lz.crm.service.ICustomerService;
import com.lz.crm.service.IEmployeeService;
import com.lz.crm.service.ISystemDictionaryItemService;
import com.lz.crm.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;

    @RequestMapping("/potentialList")
    public String list(Model model, @ModelAttribute("qo") CustomerQuery qo) {
        qo.setStatus(Customer.STATUS_COMMON);//只查询潜在客户状态
        //如果登录的员工是管理员或者经理,就可以看所有的客户,如果只是普通的销售人员,只能看自己的客户,不能看到别人的
      /*  Subject subject = SecurityUtils.getSubject();
        if(!(subject.hasRole("admin")||subject.hasRole("Market_Manager"))){
            //获取当前登录用户id
            Employee employee = (Employee) subject.getPrincipal();
            //根据当前员工id查询所跟进的客户
            qo.setSellerId(employee.getId());
        }*/

        //根据角色编码查询拥有该角色的员工 Market Market_Manager
        List<Employee> employees = employeeService.selectByRoleSn("Market", "Market_Manager");
        model.addAttribute("employees", employees);

        //职业下拉框数据
        List<SystemDictionaryItem> jobs = systemDictionaryItemService.selectByDicSn("job");
        model.addAttribute("jobs", jobs);

        //来源下拉框数据
        List<SystemDictionaryItem> sources = systemDictionaryItemService.selectByDicSn("source");
        model.addAttribute("sources", sources);

        //交流方式下拉框数据
        List<SystemDictionaryItem> ccts = systemDictionaryItemService.selectByDicSn("communicationMethod");
        model.addAttribute("ccts", ccts);

        PageInfo pageInfo = customerService.query(qo);
        model.addAttribute("result", pageInfo);
        return "customer/potentialList";
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Customer customer) {
        if (customer.getId() == null) { // 代表新增
            customerService.save(customer);
        } else { // 代表修改
            customerService.update(customer);
        }
        return new JsonResult();
    }


}
