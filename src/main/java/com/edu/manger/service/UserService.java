package com.edu.manger.service;

import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.User;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * ClassName: UserService
 * Description:
 * date: 2020/3/11 18:19
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public interface UserService {
    public  int insert(User record);

    //登录时，根据用户名获取用户
    public User findUserbyName(String username);

    public RestResponse deleteById(Integer id);

    public RestResponse update(User user);

    public RestResponse findTeacherList(User user, Integer page, Integer limit);

    public List<User> findPage(User user);

    public RestResponse saveTeacher(User user);

    public RestResponse saveStudent(User user);

    public User getUserById(Integer id);

    public RestResponse getTeacherStus(String  teacherNo,Integer page,Integer limit);


    public RestResponse getTeacherStusByCondition(String  teacherNo,Integer page,Integer limit,User user);
}
