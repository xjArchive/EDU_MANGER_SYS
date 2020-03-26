package com.edu.manger.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course {
    private Integer id;

    private String courseCode;

    private String courseName;

    private String courseType;

    private Integer courseTeacherid;

    private String startTime;

    private String address;

    private Integer score;

    private Integer courseLong;

    private Date createDate;

    private Date updateDate;

    private Integer delFlag;

    private String name;  //课程类型名

    private static final long serialVersionUID = 1L;

    public Course(String courseCode, String courseName, String courseType, String startTime, String address, Integer score, Integer courseLong, Date createDate, Date updateDate) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseType = courseType;
        this.startTime = startTime;
        this.address = address;
        this.score = score;
        this.courseLong = courseLong;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Course() {
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
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getCourseTeacherid() {
        return courseTeacherid;
    }

    public void setCourseTeacherid(Integer courseTeacherid) {
        this.courseTeacherid = courseTeacherid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCourseLong() {
        return courseLong;
    }

    public void setCourseLong(Integer courseLong) {
        this.courseLong = courseLong;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}