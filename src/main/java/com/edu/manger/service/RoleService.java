package com.edu.manger.service;

import com.edu.manger.entry.Role;
import com.edu.manger.entry.User;

/**
 * ClassName: UserService
 * Description:
 * date: 2020/3/11 18:19
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public interface RoleService {
    public  int insert(Role role);

    public Role select(Role role);

    public  int update(Role role);

    public void delete(Integer id);
}
