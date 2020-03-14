package com.edu.manger.controller;

import com.edu.manger.entry.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: LoginController
 * Description:
 * date: 2020/3/11 13:38
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Api(value = "登陆控制器",description = "登陆控制器")
@Controller
@CrossOrigin
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation("输入用户名密码登陆")
    public String login(@ApiParam("用户名") @RequestParam(value = "username",required = true) String  username,
                        @ApiParam("密码") @RequestParam(value = "password",required = true) String password, Model model){


         //shiro认证操作
        Subject subject = SecurityUtils.getSubject();
        //封装数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //登录逻辑
        try{
            subject.login(token);
            if (subject.hasRole("admin")){
                return "admin/admin_index";
            }
            if (subject.hasRole("teacher")){
                return "teacher/teacher_index";
            }
            if (subject.hasRole("student")){
                return "student/student_index";
            }
        }catch (UnknownAccountException e){
            //登录失败,用户不存在
            model.addAttribute("message","用户名不存在");
            return "../../login";
        }catch (IncorrectCredentialsException e){
            //登录失败,密码错误
            model.addAttribute("message","密码错误");
            return "../../login";
        }
        return "../../login";
    }
}
