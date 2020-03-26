package com.edu.manger.service.impl;

import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.CourseMapper;
import com.edu.manger.entry.Classs;
import com.edu.manger.entry.Course;
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
public class CourseServiceImpl implements CourseService {

    @Resource
    public CourseMapper courseMapper;

    @Override
    public RestResponse findCourseList(Course course, Integer page, Integer limit) {

       List<Course> courselist =   courseMapper.findCourseList(course);
        //分页初始化
        Page<Course> pg = PageHelper.startPage(page, limit);
        //传入list
        PageInfo pageInfo = new PageInfo(courselist);
        RestResponse restResponse = null;
        restResponse = PageUtils.StartPage(pageInfo);
       return  restResponse;
    }

    @Transactional
    @Override
    public RestResponse save(Course course) {

        if (course != null){
          int flag =  courseMapper.insert(course);
          if (flag > 0){
              return RestResponse.success(RestCode.OK);
          }
        }
        return RestResponse.error(RestCode.UNKNOW_ERROR);

    }

    @Override
    public List<Course> findList(Course course) {
      List<Course> courseList =  courseMapper.findList(course);

        return courseList;
    }

    @Transactional
    @Override
    public RestResponse update(Course course) {
        //首先判断更改的值是否与数据库表数据重复

      int flag =  courseMapper.update(course);
      if (flag > 0){
          return RestResponse.success(RestCode.OK);
      }
      return  RestResponse.error(RestCode.UNKNOW_ERROR);
    }

    @Override
    public List<Course> findPage(Course course) {
        List<Course> list =   courseMapper.findCourseList(course);
        return list;
    }

    @Override
    @Transactional
    public RestResponse deleteCourseById(Integer id) {
        int a = courseMapper.delete(id);
        if (a > 0 ){
            //删除成功
            return   RestResponse.success(RestCode.OK);
        }else{
            return RestResponse.error(RestCode.DELETE_FAIL);
        }
    }

    @Override
    public Course getByCodeOrId(Course course) {
        return  courseMapper.getByCodeOrId(course);
    }
}
