package com.edu.manger.entry;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

/**
 * ClassName: StudentGrade
 * Description:
 * date: 2020/3/24 17:12
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentGrade {

    private Integer id;
    private String stuNo;
    private String courseCode;
    private String collegeId;
    private String marks;
    private String courseName;
    private String flag;
    private String collegeName;
    private String username;
    private String realName;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
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

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
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
}
