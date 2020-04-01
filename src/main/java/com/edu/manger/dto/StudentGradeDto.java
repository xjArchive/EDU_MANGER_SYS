package com.edu.manger.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ClassName: StudentGrade
 * Description:
 * date: 2020/3/27 14:35
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentGradeDto {

    private String id;
    private String username;
    private String realName;
    private String className;
    private String colegeName;
    private String courseName;
    private String courseCode;
    private String teacherNo;
    private String stuNo;
    private String flag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getColegeName() {
        return colegeName;
    }

    public void setColegeName(String colegeName) {
        this.colegeName = colegeName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }
}
