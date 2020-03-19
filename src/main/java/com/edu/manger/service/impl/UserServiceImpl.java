package com.edu.manger.service.impl;

import com.edu.manger.constants.Constant;
import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.UserMapper;
import com.edu.manger.dao.UserRoleMapper;
import com.edu.manger.entry.User;
import com.edu.manger.entry.UserRole;
import com.edu.manger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    private UserRoleMapper userRoleMapper;



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
    public RestResponse update(User user) {
      int flag =   UserMapper.update(user);
        if (flag>0){
            return RestResponse.success(RestCode.OK);
        }else {
            return RestResponse.error(RestCode.UPDATE_FAIL);
        }
    }

    @Override
    public RestResponse findTeacherList(User user) {
        List<User> teacherList = UserMapper.findTeacherList(user);
        RestResponse restResponse = null;
        if (!teacherList.isEmpty()){
             restResponse = RestResponse.success(teacherList,Long.parseLong(teacherList.size()+""));
        }
        return 	restResponse
;
    }

    @Override
    public List<User> findPage(User user) {

         List list =    UserMapper.findTeacherList(user);
        return list;
    }

    @Override
    @Transactional
    public RestResponse saveTeacher(User user) {
        //判断用户是否存在
        User um = new User();
       um =  UserMapper.findUserbyName(user.getUsername());
        if (um !=  null){
            return RestResponse.error(RestCode.USER_EXISTED);
        }

        User un = new User();
        un.setIdCard(user.getIdCard());
        List<User> teacherList = UserMapper.findTeacherList(un);
        if (teacherList.size()>0){
            return RestResponse.error(RestCode.IDCARD_REPEATE);
        }
        //获取前端表单输入的值
        //保存至用户表中
        user.setPassword(user.getUsername());
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        UserMapper.insert(user);
        //为用户分配角色，并存至用户角色表
        UserRole ur = new UserRole();
        ur.setRoleId(Integer.valueOf(Constant.TEACHER_ROLE));
        User u = new User();
        u = UserMapper.findUserbyName(user.getUsername());
        ur.setUserId(u.getId());
        userRoleMapper.insert(ur);
        return RestResponse.success(RestCode.OK);
    }

    @Override
    public RestResponse saveStudent(User user) {
        return null;
    }
}
