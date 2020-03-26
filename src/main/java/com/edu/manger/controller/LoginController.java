package com.edu.manger.controller;

import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.User;
import com.edu.manger.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation("输入用户名密码登陆")
    public String login(@ApiParam("用户名") @RequestParam(value = "username",required = true) String  username,
                        @ApiParam("密码") @RequestParam(value = "password",required = true) String password, Model model, HttpServletRequest request){


         //shiro认证操作
        Subject subject = SecurityUtils.getSubject();
        //封装数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //登录逻辑
        try{
            subject.login(token);
            if (subject.hasRole("admin")){
                //返回个人信息至前端渲染
                User user = new User();
                user =  userService.findUserbyName(username);
                model.addAttribute("user",user);
               request.getSession().setAttribute("user",user);
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

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ApiOperation("注销")
    @ResponseBody
    public RestResponse<Object> logout(Model model, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return RestResponse.success(RestCode.OK);
    }

}
