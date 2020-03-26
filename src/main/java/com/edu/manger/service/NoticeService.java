package com.edu.manger.service;

import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.Course;
import com.edu.manger.entry.Notice;

import java.util.List;

/**
 * ClassName: NoticeService
 * Description:
 * date: 2020/3/23 11:29
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */

public interface NoticeService {

    public RestResponse findNoticeList(Notice notice, Integer page, Integer limit);

    //保存（需判断课程代码是否重复）
    public RestResponse save(Notice notice);

    public List<Notice> findList(Notice notice);

    //修改信息
    public RestResponse update(Notice notice);

    public List<Notice> findPage(Notice notice);

    public RestResponse deleteNoticeById(Integer id);


    public Notice get(Integer id);
}
