package com.edu.manger.service.impl;

import com.edu.manger.dao.UserRoleMapper;
import com.edu.manger.entry.UserRole;
import com.edu.manger.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 * Description:
 * date: 2020/3/11 18:19
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int insert(UserRole userRole) {
        return 0;
    }

    @Override
    public UserRole get(UserRole userRole) {
        return null;
    }

    @Override
    public UserRole getRoleByUserId(Integer userId) {

        UserRole userRole = userRoleMapper.getRoleByUserId(userId);
        return userRole;
    }
}
