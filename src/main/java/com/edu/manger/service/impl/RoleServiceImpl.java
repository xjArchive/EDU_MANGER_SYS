package com.edu.manger.service.impl;

import com.edu.manger.dao.RoleMapper;
import com.edu.manger.entry.Role;
import com.edu.manger.service.RoleService;

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
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;
    @Override
    public int insert(Role role) {
        return 0;
    }

    @Override
    public Role select(Role r) {
      Role role =  roleMapper.select(r);
        return role;
    }

    @Override
    public int update(Role role) {
        return 0;
    }

    @Override
    public void delete(Integer id) {

    }
}
