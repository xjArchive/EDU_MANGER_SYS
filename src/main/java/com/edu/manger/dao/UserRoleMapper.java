package com.edu.manger.dao;

import com.edu.manger.entry.UserRole;

public interface UserRoleMapper {
    int insert(UserRole userRole);

    UserRole get(UserRole userRole);

    //根据用户id获取角色id
    UserRole getRoleByUserId(Integer userId);
}