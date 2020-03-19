package com.edu.manger.dao;

import com.edu.manger.entry.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    public  int insert(User user);

    //登录时，根据用户名获取用户
    public User findUserbyName(@Param("username")String username);

    public int delete(Integer id);

    public int update(User user);

    public List<User>  findTeacherList(User user);
}