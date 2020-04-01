package com.edu.manger.service.impl;

import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.CourseMapper;
import com.edu.manger.dao.NoticeMapper;
import com.edu.manger.entry.Course;
import com.edu.manger.entry.Notice;
import com.edu.manger.service.CourseService;
import com.edu.manger.service.NoticeService;
import com.edu.manger.utils.PageUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName:
 * Description:
 * date: 2020/3/23 11:31
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    public NoticeMapper noticeMapper;

    @Override
    public RestResponse findNoticeList(Notice notice, Integer page, Integer limit) {

       List<Notice> noticelist =   noticeMapper.findNoticeList(notice);
        //分页初始化
        Page<Notice> pg = PageHelper.startPage(page, limit);
        //传入list
        PageInfo pageInfo = new PageInfo(noticelist);
        RestResponse restResponse = null;
        restResponse = PageUtils.StartPage(pageInfo);
       return  restResponse;
    }

    @Transactional
    @Override
    public RestResponse save(Notice Notice) {

        if (Notice != null){
          int flag =  noticeMapper.insert(Notice);
          if (flag > 0){
              return RestResponse.success(RestCode.OK);
          }
        }
        return RestResponse.error(RestCode.UNKNOW_ERROR);

    }

    @Override
    public List<Notice> findList(Notice notice) {
      List<Notice> noticeList =  noticeMapper.findList(notice);
        return noticeList;
    }

    @Transactional
    @Override
    public RestResponse update(Notice notice) {
        //首先判断更改的值是否与数据库表数据重复

      int flag =  noticeMapper.update(notice);
      if (flag > 0){
          return RestResponse.success(RestCode.OK);
      }
      return  RestResponse.error(RestCode.UNKNOW_ERROR);
    }

    @Override
    public List<Notice> findPage(Notice notice) {
        List<Notice> list =   noticeMapper.findList(notice);
        return list;
    }

    @Override
    @Transactional
    public RestResponse deleteNoticeById(Integer id) {
        int a = noticeMapper.delete(id);
        if (a > 0 ){
            //删除成功
            return   RestResponse.success(RestCode.OK);
        }else{
            return RestResponse.error(RestCode.DELETE_FAIL);
        }
    }

    @Override
    public Notice get(Integer id) {
        return  noticeMapper.get(id);
    }

    @Override
    public RestResponse findRecentNotice() {
        List<Notice>   noticeList = noticeMapper.findRecentNotice();
        if (noticeList.size() > 0){
            return RestResponse.success(noticeList);
        }
        return RestResponse.noData(null);
    }
}
