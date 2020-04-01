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

    private String startTime;

    private String courseLong;

    private String teacherName;

    private Integer week;
    private Integer jieci;
    private Integer type;

    private String score; //学分

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCourseLong() {
        return courseLong;
    }

    public void setCourseLong(String courseLong) {
        this.courseLong = courseLong;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getJieci() {
        return jieci;
    }

    public void setJieci(Integer jieci) {
        this.jieci = jieci;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
