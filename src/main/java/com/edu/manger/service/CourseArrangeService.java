package com.edu.manger.service;

import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.Course;
import com.edu.manger.entry.CourseArrange;

import java.util.List;

/**
 * ClassName: CourseService
 * Description:
 * date: 2020/3/23 11:29
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */

public interface CourseArrangeService {

    public RestResponse findCourseArrangeList(CourseArrange courseArrange, Integer page, Integer limit);

    //保存（需判断课程代码是否重复）
    public RestResponse save(CourseArrange courseArrange);

    public List<CourseArrange> findList(CourseArrange courseArrange);

    //修改信息
    public RestResponse update(CourseArrange courseArrange);

    public List<CourseArrange> findPage(CourseArrange courseArrange);

    public RestResponse deleteCourseArrangeById(Integer id);
}
