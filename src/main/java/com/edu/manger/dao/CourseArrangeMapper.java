package com.edu.manger.dao;

import com.edu.manger.entry.Course;
import com.edu.manger.entry.CourseArrange;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseArrangeMapper {
    int insert(CourseArrange courseArrange);
    public List<CourseArrange> findCourseArrangeList(CourseArrange courseArrange);
    public List<CourseArrange> findList(CourseArrange courseArrange);
    public int update(CourseArrange courseArrange);
    public int delete(Integer id);
}