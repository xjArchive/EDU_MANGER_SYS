package com.edu.manger.entry;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ClassName: CourseArrange
 * Description:
 * date: 2020/3/23 18:06
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseArrange {

    private Integer id;

    private String courseCode;

    private String courseName;

    private String courseAddress;

    private String collegeName;

    private String teacherNo;

    private String className;

    public CourseArrange(Integer id, String courseCode, String courseName, String courseAddress, String collegeName, String teacherNo) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseAddress = courseAddress;
        this.collegeName = collegeName;
        this.teacherNo = teacherNo;
    }

    public CourseArrange() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseAddress() {
        return courseAddress;
    }

    public void setCourseAddress(String courseAddress) {
        this.courseAddress = courseAddress;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
