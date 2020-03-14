package com.edu.manger.service;

import com.edu.manger.entry.User;
import com.edu.manger.entry.UserRole;

/**
 * ClassName: UserService
 * Description:
 * date: 2020/3/11 18:19
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public interface UserRoleService {
    int insert(UserRole userRole);

    UserRole get(UserRole userRole);

    //根据用户id获取角色id
    UserRole getRoleByUserId(Integer userId);
}
