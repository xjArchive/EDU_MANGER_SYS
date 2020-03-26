package com.edu.manger.service.impl;

import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.CourseArrangeMapper;
import com.edu.manger.dao.CourseMapper;
import com.edu.manger.entry.Course;
import com.edu.manger.entry.CourseArrange;
import com.edu.manger.service.CourseArrangeService;
import com.edu.manger.service.CourseService;
import com.edu.manger.utils.PageUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: CourseServiceImpl
 * Description:
 * date: 2020/3/23 11:31
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Service
public class CourseArrangeServiceImpl implements CourseArrangeService {

    @Resource
    public CourseArrangeMapper courseArrangeMapper;

    @Override
    public RestResponse findCourseArrangeList(CourseArrange courseArrange, Integer page, Integer limit) {

       List<CourseArrange> courseArrangelist =   courseArrangeMapper.findCourseArrangeList(courseArrange);
        //分页初始化
        Page<CourseArrange> pg = PageHelper.startPage(page, limit);
        //传入list
        PageInfo pageInfo = new PageInfo(courseArrangelist);
        RestResponse restResponse = null;
        restResponse = PageUtils.StartPage(pageInfo);
       return  restResponse;
    }

    @Transactional
    @Override
    public RestResponse save(CourseArrange courseArrange) {

        if (courseArrange != null){
          int flag =  courseArrangeMapper.insert(courseArrange);
          if (flag > 0){
              return RestResponse.success(RestCode.OK);
          }
        }
        return RestResponse.error(RestCode.UNKNOW_ERROR);

    }

    @Override
    public List<CourseArrange> findList(CourseArrange courseArrange) {
      List<CourseArrange> courseList =  courseArrangeMapper.findList(courseArrange);

        return courseList;
    }

    @Transactional
    @Override
    public RestResponse update(CourseArrange courseArrange) {
        //首先判断更改的值是否与数据库表数据重复

      int flag =  courseArrangeMapper.update(courseArrange);
      if (flag > 0){
          return RestResponse.success(RestCode.OK);
      }
      return  RestResponse.error(RestCode.UNKNOW_ERROR);
    }

    @Override
    public List<CourseArrange> findPage(CourseArrange courseArrange) {
        List<CourseArrange> list =   courseArrangeMapper.findCourseArrangeList(courseArrange);
        return list;
    }

    @Override
    @Transactional
    public RestResponse deleteCourseArrangeById(Integer id) {
        int a = courseArrangeMapper.delete(id);
        if (a > 0 ){
            //删除成功
            return   RestResponse.success(RestCode.OK);
        }else{
            return RestResponse.error(RestCode.DELETE_FAIL);
        }
    }
}
