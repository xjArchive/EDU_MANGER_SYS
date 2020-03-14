package com.edu.manger.controller;

import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.User;
import com.edu.manger.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: AdminController
 * Description:
 * date: 2020/3/12 22:10
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/admin")
@Api(value = "管理员数据控制器", description = "管理员数据控制器")
@CrossOrigin
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * 由前端传入条件，获取教师列表
     * @return
     */
    @ApiOperation("加载教师列表")
    @RequestMapping(value = "/getAllTeacher",method = RequestMethod.GET)
    public RestResponse getAllTeacher(){
        User user = new User();
        user.setFlag(2);
        RestResponse result = userService.findTeacherList(user);
        return result;
    }

    /**
     * 由前端传入条件，获取教师列表
     * @return
     */
    @ApiOperation("添加教师")
    @RequestMapping(value = "/AddTeacher",method = RequestMethod.POST)
    public RestResponse AddTeacher(){
        System.out.println("前端来了");
       return RestResponse.success();
    }
}
