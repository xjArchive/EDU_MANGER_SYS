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

    /* ==================教师模块视图==================================*/

    @RequestMapping(value = {"/getStudentListView",""},method = RequestMethod.GET)
    @ApiOperation("点击学生管理，返回页面，获取所教班级的基本学生信息，只能查看不能编辑")
    public String getStudentListView(){
        return "teacher/teacher_getMyStudentList";
    }


    @RequestMapping(value = {"/MyCourseView",""},method = RequestMethod.GET)
    @ApiOperation("获取我的授课安排")
    public String MyCourseView(){
        return "teacher/teacher_getMyCourse";
    }


    @RequestMapping(value = {"/getMyStudentGrade",""},method = RequestMethod.GET)
    @ApiOperation("获取我的授课安排")
    public String getMyStudentGrade(){
        return "teacher/teacher_myStudentGrade";
    }


    /* ==================学生模块视图==================================*/

    @RequestMapping(value = {"/StudentCourse",""},method = RequestMethod.GET)
    @ApiOperation("获取我的课表")
    public String StudentCourse(){
        return "student/student_studentCourse";
    }

    @RequestMapping(value = {"/selectCourse",""},method = RequestMethod.GET)
    @ApiOperation("网上选课")
    public String selectCourse(){
        return "student/student_selectCourse";
    }

    @RequestMapping(value = {"/getMyGrade",""},method = RequestMethod.GET)
    @ApiOperation("获取我的成绩")
    public String getMyGrade(){
        return "student/student_studentGrade";
    }

    @ApiOperation("提交事务")
    @RequestMapping(value = {"/commitTran",""},method = RequestMethod.GET)
    public String commitTran(){
        return "student/student_commitTran";
    }

    @ApiOperation("我的事务")
    @RequestMapping(value = {"/myTran",""},method = RequestMethod.GET)
    public String myTran(){
        return "student/student_tranList";
    }


    @ApiOperation("老师提交事务")
    @RequestMapping(value = {"/t_commitTran",""},method = RequestMethod.GET)
    public String t_commitTran(){
        return "teacher/teacher_commitTran";
    }

    @ApiOperation("教师事务")
    @RequestMapping(value = {"/t_tranList",""},method = RequestMethod.GET)
    public String t_tranList(){
        return "teacher/teacher_tranList";
    }

    @ApiOperation("获取所有事务")
    @RequestMapping(value = {"/getAllTran",""},method = RequestMethod.GET)
    public String getAllTran(){
        return "admin/admin_AllTran";
    }

    @ApiOperation("学生选题列表")
    @RequestMapping(value = {"/selectList",""},method = RequestMethod.GET)
    public String selectList(){
        return "admin/admin_selectList";
    }

    @ApiOperation("毕设选题")
    @RequestMapping(value = {"/select",""},method = RequestMethod.GET)
    public String select(){
        return "admin/admin_select";
    }



    @ApiOperation("学生毕设选题")
    @RequestMapping(value = {"/selectView",""},method = RequestMethod.GET)
    public String selectView(){
        return "student/student_selectList";
    }

    @ApiOperation("通知公告页面")
    @RequestMapping(value = {"/noticeView",""},method = RequestMethod.GET)
    public String noticeView(){
        return "message";
    }


}
