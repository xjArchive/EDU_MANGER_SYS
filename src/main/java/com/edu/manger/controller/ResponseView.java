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

}
