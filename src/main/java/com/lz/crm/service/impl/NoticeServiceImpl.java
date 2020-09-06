package com.lz.crm.service.impl;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.crm.domain.Notice;
import com.lz.crm.mapper.NoticeMapper;
import com.lz.crm.query.QueryObject;
import com.lz.crm.service.INoticeService;
import com.lz.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class NoticeServiceImpl implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public void save(Notice notice) {
        notice.setIssuer(UserContext.getCurrentUser());
        notice.setPubdate(new Date());
        noticeMapper.insert(notice);
    }

    @Override
    public void delete(Long id) {
        noticeMapper.deleteByPrimaryKey(id);
        noticeMapper.deleteRead(id);
    }

    @Override
    public void update(Notice notice) {
        notice.setPubdate(new Date());
        noticeMapper.updateByPrimaryKey(notice);
    }

    @Override
    public Notice get(Long id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Notice> listAll() {
        return noticeMapper.selectAll();
    }

    @Override
    public void saveRead(Long noticeId, Long empId) {
        int read = noticeMapper.getRead(noticeId, empId);
        if (read==0){noticeMapper.saveRead(noticeId,empId);}

    }

    @Override
    public PageInfo<Notice> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize()); //对下一句sql进行自动分页
        List<Notice> notices = noticeMapper.selectForList(qo); //里面不需要自己写limit
        return new PageInfo<Notice>(notices);
    }
}
