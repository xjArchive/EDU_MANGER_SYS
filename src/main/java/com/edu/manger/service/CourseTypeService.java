package com.edu.manger.service;

import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.CourseType;

import java.util.List;

/**
 * ClassName: CourseTypeService
 * Description:
 * date: 2020/3/23 13:33
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public interface CourseTypeService {
    RestResponse save(CourseType courseType);
    List<CourseType> findList(CourseType courseType);
    int judgeNameExists(CourseType courseType);
}
