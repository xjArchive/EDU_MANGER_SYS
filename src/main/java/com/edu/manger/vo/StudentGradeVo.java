package com.edu.manger.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * ClassName: StudentGradeVo
 * Description:
 * date: 2020/3/24 17:24
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public class StudentGradeVo implements Serializable {

    private String id;  //选课成绩表id
    private String stuNo;
    private String realName;
    private String courseName;
    private String courseCode;
    private String collegeName;
    private String marks;
    private String flag;  //是否打分
    private String className;

    private String courseType;
    private String pass;
    private String teacherName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getPass() {
        return pass;
    }


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public StudentGradeVo() {
    }

    public StudentGradeVo(String id, String stuNo, String realName, String courseName, String courseCode,  String collegeName, String marks, String flag) {
        this.id = id;
        this.stuNo = stuNo;
        this.realName = realName;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.collegeName = collegeName;
        this.marks = marks;
        this.flag = flag;
    }
}
