package com.edu.manger.controller;

import com.edu.manger.entry.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: ResponseView
 * Description:
 * date: 2020/3/12 17:42
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/View")
@Api(value = "视图控制器", description = "视图控制器")
public class ResponseView {

    @RequestMapping(value = {"/teacherListView",""},method = RequestMethod.GET)
    @ApiOperation("点击教师管理，返回页面")
    public String getTacherList( HttpServletRequest request, HttpServletResponse response, Model model){
        return "admin/admin_showTeacher";
    }

    @RequestMapping(value = {"/studentListView",""},method = RequestMethod.GET)
    @ApiOperation("点击学生管理，返回页面")
    public String getStudentList( HttpServletRequest request, HttpServletResponse response, Model model){
        return "admin/admin_showStudent";
    }

    @RequestMapping(value = {"/collegeListView",""},method = RequestMethod.GET)
    @ApiOperation("点击院系管理，返回页面")
    public String getCollegeList( HttpServletRequest request, HttpServletResponse response, Model model){
        return "admin/admin_college";
    }

    @RequestMapping(value = {"/mainView",""},method = RequestMethod.GET)
    @ApiOperation("点击校园概览，返回页面")
    public String getMain(){
        return "admin/admin_main";
    }

    @RequestMapping(value = {"/classListView",""},method = RequestMethod.GET)
    @ApiOperation("点击班级管理，返回页面")
    public String getClassList( HttpServletRequest request, HttpServletResponse response, Model model){
        return "admin/admin_class";
    }

    @RequestMapping(value = {"/personView",""},method = RequestMethod.GET)
    @ApiOperation("点击个人中心，返回页面")
    public String getPersion(){
        return "admin/admin_person";
    }

    @RequestMapping(value = {"/modifyPwsView",""},method = RequestMethod.GET)
    @ApiOperation("点击修改密码，返回页面")
    public String modifyPwd(){
        return "admin/admin_modifyPwd";
    }

    @RequestMapping(value = {"/courseListView",""},method = RequestMethod.GET)
    @ApiOperation("点击课程管理，返回页面")
    public String getCourseList(){
        return "admin/admin_course";
    }

    @RequestMapping(value = {"/couseMangerListView",""},method = RequestMethod.GET)
    @ApiOperation("点击排课管理，返回页面")
    public String getCourseArrangeList(){
        return "admin/admin_courseArrange";
    }


    @RequestMapping(value = {"/noticeListView",""},method = RequestMethod.GET)
    @ApiOperation("点击排课管理，返回页面")
    public String getNoticeList(){
        return "admin/admin_notice";
    }

    @RequestMapping(value = {"/studentGradeView",""},method = RequestMethod.GET)
    @ApiOperation("点击排课管理，返回页面")
    public String studentGradeView(){
        return "admin/admin_studentGrade";
    }

}
