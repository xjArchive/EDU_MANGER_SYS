package com.edu.manger.dao;

import com.edu.manger.entry.CourseType;
import com.edu.manger.entry.Dept;

import java.util.List;

/**
 * ClassName: CourseTypeMapper
 * Description:
 * date: 2020/3/23 13:25
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public interface CourseTypeMapper {

    List<CourseType> findList(CourseType courseType);
    CourseType get(Integer id);
    int delete(Integer id);
    int insert(CourseType courseType);
    //判断类型名称不能重复
    public List<CourseType> judgeNameExists(CourseType courseType);

}
