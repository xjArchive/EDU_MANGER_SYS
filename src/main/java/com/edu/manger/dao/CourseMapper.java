package com.edu.manger.dao;

import com.edu.manger.entry.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
    int insert(Course course);
    public List<Course> findCourseList(Course course);
    public List<Course> findList(Course course);
    public int update(Course course);
    public int delete(Integer id);

    public Course getByCodeOrId(Course course);
}