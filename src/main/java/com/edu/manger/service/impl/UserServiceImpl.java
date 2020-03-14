package com.edu.manger.service.impl;

import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.UserMapper;
import com.edu.manger.entry.User;
import com.edu.manger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: UserServiceImpl
 * Description:
 * date: 2020/3/11 18:19
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper UserMapper;



    @Override
    public int insert(User record) {
        return 0;
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Override
    public User findUserbyName(String username) {

        User userbyName = UserMapper.findUserbyName(username);
        return userbyName;
    }

    @Override
    public int delete(Integer id) {
      int a =  UserMapper.delete(id);
        return a;
    }

    @Override
    public int update(Integer id) {
        return 0;
    }

    @Override
    public RestResponse findTeacherList(User user) {
        List<User> teacherList = UserMapper.findTeacherList(user);
        RestResponse restResponse = null;
        if (!teacherList.isEmpty()){
             restResponse = RestResponse.success(teacherList);
        }
        return 	restResponse
;
    }
}
