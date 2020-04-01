package com.edu.manger.dao;

import com.edu.manger.entry.Classs;
import com.edu.manger.entry.Notice;

import java.util.List;

/**
 *
 */
public interface NoticeMapper {
    int insert(Notice notice);


    public List<Notice> findNoticeList(Notice notice);

    public List<Notice> findList(Notice notice);

    public int delete(Integer id);

    public int update(Notice notice);

    public Notice get(Integer id);

    public List<Notice> findRecentNotice();

}
