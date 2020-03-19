package com.edu.manger.controller;

import com.edu.manger.constants.Constant;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.User;
import com.edu.manger.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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


    @ApiOperation("根据相关条件搜索")
    @RequestMapping(value = "/getListByCondition",method = RequestMethod.GET)
    public RestResponse getListByCondition(@ApiParam(value = "页码",required = true)@RequestParam String page, @ApiParam(value = "每页数据条数",required = true)@RequestParam String limit,@ApiParam(value = "前端传入的用户对象") User user){
        //使用分页插件
        PageHelper.startPage(Integer.parseInt(page) , Integer.parseInt(limit));
        //根据相关条件搜索
        user.setFlag(2);//教师列表标志
         List userList =  userService.findPage(user);
      //根据查询的数据列表，得到分页的结果对象
        RestResponse restResponse = null;
        List list = Lists.newArrayList();
        if (userList.size() > 0){
            //用户名唯一
            PageInfo<User> pageList = new PageInfo<User>(userList);
            list  = pageList.getList();
            long count = pageList.getTotal();
            restResponse =   RestResponse.success(list,count);
        }else {
            restResponse =  RestResponse.noData(null);
        }
        return  restResponse;

    }

    /**
     * 由前端传入条件，获取教师列表
     * @return
     */
    @ApiOperation("添加教师")
    @RequestMapping(value = "/AddTeacher",method =RequestMethod.POST)
    public RestResponse AddTeacher(@ApiParam(name = "username",value = "工号",required = true) String username,
                                   @ApiParam(name = "sex",value = "性别 1 男 0女",required = true) String sex,
                                   @ApiParam(name = "birth",value = "出生年月") String birth,
                                   @ApiParam(name = "mobile",value = "手机号码",required = true) String mobile,
                                   @ApiParam(name = "degree",value = "学历",required = true) String degree,
                                   @ApiParam(name = "level",value = "职称",required = true) String level,
                                   @ApiParam(name = "idCard",value = "身份证",required = true) String idCard,
                                   @ApiParam(name = "realName",value = "真实姓名",required = true) String realName,
                                   @ApiParam(name = "collegeId",value = "所属系",required = true) String collegeId){
       //接受前端传入的值,并存入user对象
        User user = new User();
        user.setUsername(username);
        user.setSex(Integer.valueOf(sex));
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           date = sdf.parse(birth);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirth(date);
        user.setMobile(mobile);
        user.setIdCard(idCard);
        user.setFlag(Integer.valueOf(Constant.TEACHER_ROLE));
        user.setCollegeId(Integer.valueOf(collegeId));
        user.setRealName(realName);
        user.setDegree(degree);
        user.setLevel(level);
       RestResponse response =  userService.saveTeacher(user);
       return response;
    }



    /**
     * 由前端传入条件，获取教师列表
     * @return
     */
    @ApiOperation("修改教师")
    @RequestMapping(value = "/UpdateTeacher",method =RequestMethod.POST)
    public RestResponse UpdateTeacher(
                                   @ApiParam(name = "id",value = "id",required = true) String id,
                                   @ApiParam(name = "username",value = "工号",required = true) String username,
                                   @ApiParam(name = "sex",value = "性别 1 男 0女",required = true) String sex,
                                   @ApiParam(name = "birth",value = "出生年月") String birth,
                                   @ApiParam(name = "mobile",value = "手机号码",required = true) String mobile,
                                   @ApiParam(name = "degree",value = "学历",required = true) String degree,
                                   @ApiParam(name = "level",value = "职称",required = true) String level,
                                   @ApiParam(name = "idCard",value = "身份证",required = true) String idCard,
                                   @ApiParam(name = "realName",value = "真实姓名",required = true) String realName,
                                   @ApiParam(name = "collegeId",value = "所属系",required = true) String collegeId){
        //接受前端传入的值,并存入user对象
        User user = new User();
        user.setUsername(username);
        user.setId(Integer.valueOf(id));
        user.setSex(Integer.valueOf(sex));
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(birth);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirth(date);
        user.setMobile(mobile);
        user.setIdCard(idCard);
        user.setFlag(Integer.valueOf(Constant.TEACHER_ROLE));
        user.setCollegeId(Integer.valueOf(collegeId));
        user.setRealName(realName);
        user.setDegree(degree);
        user.setLevel(level);
        RestResponse response =  userService.update(user);
        return response;
    }



    /**
     * 接受前端传入的id,并删除指定用户，此处我是用逻辑删除
     * @return
     */
    @ApiOperation("删除用户")
    @RequestMapping(value = "/delUserById",method = RequestMethod.POST)
    public RestResponse delUserById(@ApiParam(name = "userId",required = true,value = "用户表id") String userId){
        userService.delete(Integer.valueOf(userId));
        return RestResponse.success();
    }
}
