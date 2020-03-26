package com.edu.manger.service;

import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.Classs;
import com.edu.manger.entry.Course;

import java.util.List;

/**
 * ClassName: CourseService
 * Description:
 * date: 2020/3/23 11:29
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */

public interface CourseService  {

    public RestResponse findCourseList(Course course, Integer page, Integer limit);

    //保存（需判断课程代码是否重复）
    public RestResponse save(Course course);

    public List<Course> findList(Course course);

    //修改信息
    public RestResponse update(Course course);

    public List<Course> findPage(Course course);

    public RestResponse deleteCourseById(Integer id);

    //通过代码或id获取才是唯一的
    public Course getByCodeOrId(Course course);
}
