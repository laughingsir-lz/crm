package com.lz.crm.web.controller;

import com.lz.crm.domain.CustomerTraceHistory;
import com.lz.crm.query.QueryObject;
import com.lz.crm.service.ICustomerTraceHistoryService;
import com.lz.crm.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customerTraceHistory")
public class CustomerTraceHistoryController {

    @Autowired
    private ICustomerTraceHistoryService customerTraceHistoryService;

    // 提供一个方法处理查询所有部门数据请求，响应 HTML
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        PageInfo pageInfo = customerTraceHistoryService.query(qo);
        model.addAttribute("result", pageInfo);
        return "customerTraceHistory/list"; // /WEB-INF/views/customerTraceHistory/potentialList.ftl
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(CustomerTraceHistory customerTraceHistory) {
        if (customerTraceHistory.getId() == null) { // 代表新增
            customerTraceHistoryService.save(customerTraceHistory);
        } else { // 代表修改
            customerTraceHistoryService.update(customerTraceHistory);
        }
        return new JsonResult();
    }

}
