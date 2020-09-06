package com.lz.crm.web.controller;


import com.github.pagehelper.PageInfo;
import com.lz.crm.domain.Notice;
import com.lz.crm.query.QueryObject;
import com.lz.crm.service.INoticeService;
import com.lz.crm.util.JsonResult;
import com.lz.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zzz
 */
@Controller
@RequestMapping("/notice")

public class NoticeController {
    @Autowired
    private INoticeService noticeService;
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        qo.setCurrentUserId(UserContext.getCurrentUser().getId());
        PageInfo<Notice> pageInfo = noticeService.query(qo);
        model.addAttribute("result", pageInfo);
        return "/notice/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            noticeService.delete(id);
        }
        return new JsonResult();
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Notice notice) {
        if (notice.getId() != null) {
            noticeService.update(notice);
        } else {
            noticeService.save(notice);
        }
        return new JsonResult();
    }

    @RequestMapping("/content")
    public String content(Model model,Long id,int read) {

        Notice notice = noticeService.get(id);
        if(read==0){
            noticeService.saveRead(id,UserContext.getCurrentUser().getId());
        }
        model.addAttribute("notice", notice);
        return "notice/show";
    }
}
