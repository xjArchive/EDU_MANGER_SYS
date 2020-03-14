package com.edu.manger.dao;

import com.edu.manger.entry.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMapper {
    int insert(Course course);
}