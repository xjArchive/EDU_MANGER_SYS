package com.edu.manger.service.impl;

import com.edu.manger.constants.Constant;
import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.*;
import com.edu.manger.entry.*;
import com.edu.manger.service.UserService;
import com.edu.manger.utils.PageUtils;
import com.edu.manger.utils.PasswordMd5Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private ClassMapper classMapper;

    @Resource
    private StudentGradeMapper studentGradeMapper;

    @Resource
    private CourseArrangeMapper courseArrangeMapper;

    @Resource
    private DeptMapper deptMapper;




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
    @Transactional
    public RestResponse deleteById(Integer id) {
        User user = new User();
      User u = UserMapper.get(id);
      //如果要删除的是学生，那么将该用户所属的课程成绩表清空
      if (Constant.STUDENT_ROLE.equals(u.getFlag().toString())){
          StudentGrade studentGrade = new StudentGrade();
          studentGrade.setStuNo(u.getUsername());
        List<StudentGrade> studentGradeList = studentGradeMapper.findList(studentGrade);
        if (!studentGradeList.isEmpty()){
                studentGradeMapper.deleteAllByStu(u.getUsername());
        }
      }
      int a =  UserMapper.delete(id);
      if (a > 0){
          return RestResponse.success(RestCode.OK);
      }
        return RestResponse.error(RestCode.DELETE_FAIL);
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
    public RestResponse findTeacherList(User user,Integer page, Integer limit) {
        List<User> teacherList = UserMapper.findTeacherList(user);
        //分页初始化
        Page<User> pg = PageHelper.startPage(page, limit);
        //传入list
        PageInfo pageInfo = new PageInfo(teacherList);
        RestResponse restResponse = null;
        restResponse = PageUtils.StartPage(pageInfo);
       /* if (!teacherList.isEmpty()){
             restResponse = RestResponse.success(teacherList,Long.parseLong(teacherList.size()+""));
        }*/
        return 	restResponse
;
    }

    @Override
    public List<User> findPage(User user) {

         List<User> list =   UserMapper.findTeacherList(user);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        user.setPassword(PasswordMd5Utils.passwordByMd5(user.getUsername()));
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
    @Transactional(rollbackFor = Exception.class)
    public RestResponse saveStudent(User user) {
        //判断用户是否存在
        User um = new User();
        um =  UserMapper.findUserbyName(user.getUsername());
        if (um !=  null){
            return RestResponse.error(RestCode.USER_EXISTED);
        }

        User un = new User();
        un.setIdCard(user.getIdCard());
        List<User> teacherList = UserMapper.findTeacherList(un);  //此方法学生与教师共用，都是获取用户列表
        if (teacherList.size()>0){
            return RestResponse.error(RestCode.IDCARD_REPEATE);
        }
        //获取前端表单输入的值
        //保存至用户表中
        user.setPassword(PasswordMd5Utils.passwordByMd5(user.getUsername())); //初始密码为用户名
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        UserMapper.insert(user);
        //为用户分配角色，并存至用户角色表
        UserRole ur = new UserRole();
        ur.setRoleId(Integer.valueOf(Constant.STUDENT_ROLE));
        User u = new User();
        u = UserMapper.findUserbyName(user.getUsername());
        ur.setUserId(u.getId());
        userRoleMapper.insert(ur);
        //同时获取课程安排表的课程，存入选课表
        //获取班级id,进而获取班级名称
        Classs cls = new Classs();
        cls.setId(u.getClassId());
          Classs classs = classMapper.get(cls);
        CourseArrange courseArrange =new CourseArrange();
        courseArrange.setClassName(classs.getClassName());
        //将该班级的所有课程获取出来
         List<CourseArrange> courseArrangeList = courseArrangeMapper.findList(courseArrange);
        if (courseArrangeList.size() > 0){
            for (CourseArrange ca : courseArrangeList){
                StudentGrade studentGrade = new StudentGrade();
                studentGrade.setCourseCode(ca.getCourseCode());
                studentGrade.setStuNo(u.getUsername());
                studentGrade.setFlag(Constant.NOT_SCORE_FLAG);
                studentGrade.setCollegeId(u.getCollegeId().toString());
                studentGradeMapper.insert(studentGrade);
            }
        }
        return RestResponse.success(RestCode.OK);
    }


    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {

        User user = new User();
        user =  UserMapper.get(id);
        return user;
    }

    @Override
    public RestResponse getTeacherStus(String teacherNo,Integer page,Integer limit) {

        //新建一个List用来存储该教师所教学生的信息
        List<User> userList = Lists.newArrayList();
        CourseArrange courseArrange = new CourseArrange();
        courseArrange.setTeacherNo(teacherNo);
        //根据教师工号，查询该教师所授的班级,将所授的班级用集合存储进来
       List<CourseArrange> courseArrangeList = courseArrangeMapper.findList(courseArrange);
       List<String> classNameList = Lists.newArrayList();
        if (courseArrangeList.size() > 0){
            courseArrangeList.forEach(tmp -> {
                classNameList.add(tmp.getClassName());
            });
            //对班级名称进行去重操作，保证班级名唯一(jdk新特性去重)
         List<String> list= classNameList.stream().distinct().collect(Collectors.toList());
         List<Classs> classIds = Lists.newArrayList();
         if (!list.isEmpty()){
             list.forEach(tmp ->{  //遍历班级名称，获取班级id
                 Classs classs = new Classs();
                 classs.setClassName(tmp);
                 Classs cls =  classMapper.get(classs); //获取的班级
                 classIds.add(cls);
             });
         }
         if (!classIds.isEmpty()){
             classIds.forEach(tmp ->{
                 User user = new User();
                 user.setFlag(Integer.valueOf(Constant.STUDENT_ROLE));
                 user.setClassId(tmp.getId());
               List<User> stuList = UserMapper.findTeacherList(user);
                 userList.addAll(stuList);
             });
         }
            userList.forEach(tmp ->{
                Classs classs = new Classs();
                classs.setId(tmp.getClassId());
                tmp.setClassName(classMapper.get(classs).getClassName());
                tmp.setCollegeName(deptMapper.get(tmp.getCollegeId()).getDeptName());

            });
         PageHelper.startPage(page,limit);
         PageInfo pageInfo  = new PageInfo(userList);
         RestResponse restResponse = PageUtils.StartPage(pageInfo);
            return restResponse;

        }

        return RestResponse.noData(null);
    }


    /**
     * 根据条件搜索学生
     * @param teacherNo
     * @param page
     * @param limit
     * @param
     * @return
     */
    @Override
    public RestResponse getTeacherStusByCondition(String teacherNo, Integer page, Integer limit, User us) {

        //新建一个List用来存储该教师所教学生的信息
        List<User> userList = Lists.newArrayList();
        CourseArrange courseArrange = new CourseArrange();
        courseArrange.setTeacherNo(teacherNo);
        //根据教师工号，查询该教师所授的班级,将所授的班级用集合存储进来
        List<CourseArrange> courseArrangeList = courseArrangeMapper.findList(courseArrange);
        List<String> classNameList = Lists.newArrayList();
        if (courseArrangeList.size() > 0){
            courseArrangeList.forEach(tmp -> {
                classNameList.add(tmp.getClassName());
            });
            //对班级名称进行去重操作，保证班级名唯一(jdk新特性去重)
            List<String> list= classNameList.stream().distinct().collect(Collectors.toList());
            List<Classs> classIds = Lists.newArrayList();
            if (!list.isEmpty()){
                list.forEach(tmp ->{  //遍历班级名称，获取班级id
                    Classs classs = new Classs();
                    classs.setClassName(tmp);
                    Classs cls =  classMapper.get(classs); //获取的班级
                    classIds.add(cls);
                });
            }
            if (!classIds.isEmpty()){
                classIds.forEach(tmp ->{
                    User user = new User();
                    user.setFlag(Integer.valueOf(Constant.STUDENT_ROLE));
                    user.setClassId(tmp.getId());
                    if (StringUtils.isNotBlank(us.getUsername())){
                        user.setUsername(us.getUsername());
                    }
                    if (StringUtils.isNotBlank(us.getIdCard())){
                        user.setIdCard(us.getIdCard());
                    }
                    List<User> stuList = UserMapper.findTeacherList(user);
                    userList.addAll(stuList);
                });
            }
            userList.forEach(tmp ->{
                Classs classs = new Classs();
                classs.setId(tmp.getClassId());
                tmp.setClassName(classMapper.get(classs).getClassName());
                tmp.setCollegeName(deptMapper.get(tmp.getCollegeId()).getDeptName());

            });
            PageHelper.startPage(page,limit);
            PageInfo pageInfo  = new PageInfo(userList);
            RestResponse restResponse = PageUtils.StartPage(pageInfo);
            return restResponse;

        }

        return RestResponse.noData(null);

    }
}
