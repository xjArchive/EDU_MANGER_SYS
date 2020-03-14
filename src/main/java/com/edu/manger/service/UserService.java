package com.edu.manger.service;

import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.User;

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

    public int delete(Integer id);

    public int update(Integer id);

    public RestResponse findTeacherList(User user);
}
