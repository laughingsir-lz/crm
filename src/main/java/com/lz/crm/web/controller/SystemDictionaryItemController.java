package com.lz.crm.web.controller;

import com.lz.crm.domain.SystemDictionaryItem;
import com.lz.crm.query.SystemDictionaryItemQuery;
import com.lz.crm.service.ISystemDictionaryItemService;
import com.lz.crm.service.ISystemDictionaryService;
import com.lz.crm.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemDictionaryItem")
public class SystemDictionaryItemController {

    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;

    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") SystemDictionaryItemQuery qo) {
        PageInfo pageInfo = systemDictionaryItemService.query(qo);
        model.addAttribute("result", pageInfo);
        //查询所有字典目录
        model.addAttribute("systemDictionarys", systemDictionaryService.listAll());
        return "systemDictionaryItem/list";
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(SystemDictionaryItem systemDictionaryItem) {
        if (systemDictionaryItem.getId() == null) { // 代表新增
            systemDictionaryItemService.save(systemDictionaryItem);
        } else { // 代表修改
            systemDictionaryItemService.update(systemDictionaryItem);
        }
        return new JsonResult();
    }


}
