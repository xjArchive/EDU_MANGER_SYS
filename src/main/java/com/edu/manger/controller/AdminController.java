package com.edu.manger.controller;

import com.edu.manger.constants.Constant;
import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.ClassMapper;
import com.edu.manger.entry.*;
import com.edu.manger.service.*;
import com.edu.manger.utils.PageUtils;
import com.edu.manger.utils.getIpAddressUtils;
import com.edu.manger.vo.StudentGradeVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ClassName: AdminController
 * Description:
 * date: 2020/3/12 22:10
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/admin")
@Api(value = "管理员数据控制器", description = "管理员数据控制器")
@CrossOrigin
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private ClassService classService;


    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTypeService courseTypeService;

    @Autowired
    private CourseArrangeService courseArrangeService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private StudentGradeService studentGradeService;

    @Autowired
    private TranService tranService;

    @Autowired
    private SelectService selectService;

    @Autowired
    private SelectListService selectListService;

    /**
     * 由前端传入条件，获取教师列表
     * @return
     */
    @ApiOperation("加载教师列表")
    @RequestMapping(value = "/getAllTeacher",method = RequestMethod.GET)
    public RestResponse getAllTeacher(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit){
        User user = new User();
        user.setFlag(Integer.valueOf(Constant.TEACHER_ROLE));
        RestResponse result = userService.findTeacherList(user,page,limit);
        return result;
    }

    /**
     * 由前端传入条件，获取教师列表
     * @return
     */
    @ApiOperation("加载学生列表")
    @RequestMapping(value = "/getAllStudent",method = RequestMethod.GET)
    public RestResponse getAllStudent(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit){
        User user = new User();
        user.setFlag(Integer.valueOf(Constant.STUDENT_ROLE));
        RestResponse result = userService.findTeacherList(user,page,limit); //该方法适用于教师和学生
        return result;
    }


    /**
     * 由前端传入条件，获取院系列表
     * @return
             */
    @ApiOperation("加载院系列表")
    @RequestMapping(value = "/getAllCollege",method = RequestMethod.GET)
    public RestResponse getAllCollege(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit){
        Dept dept = new Dept();

        RestResponse result = deptService.findCollegeList(dept,page,limit);
        return result;
    }



    @ApiOperation("根据相关条件搜索")
    @RequestMapping(value = "/getListByCondition",method = RequestMethod.GET)
    public RestResponse getListByCondition(@ApiParam(value = "页码",required = true)@RequestParam String page, @ApiParam(value = "每页数据条数",required = true)@RequestParam String limit,@ApiParam(value = "前端传入的用户对象") User user){
        //使用分页插件
        PageHelper.startPage(Integer.parseInt(page) , Integer.parseInt(limit));
        //根据相关条件搜索
        user.setFlag(2);//教师列表标志
         List userList =  userService.findPage(user);
      //根据查询的数据列表，得到分页的结果对象
        RestResponse restResponse = null;
        List list = Lists.newArrayList();
        if (userList.size() > 0){
            //用户名唯一
            PageInfo<User> pageList = new PageInfo<User>(userList);
            list  = pageList.getList();
            long count = pageList.getTotal();
            restResponse =   RestResponse.success(list,count);
        }else {
            restResponse =  RestResponse.noData(null);
        }
        return  restResponse;

    }

    /**
     * 由前端传入条件，获取教师列表
     * @return
     */
    @ApiOperation("添加教师")
    @RequestMapping(value = "/AddTeacher",method =RequestMethod.POST)
    public RestResponse AddTeacher(@ApiParam(name = "username",value = "工号",required = true) String username,
                                   @ApiParam(name = "sex",value = "性别 1 男 0女",required = true) String sex,
                                   @ApiParam(name = "birth",value = "出生年月") String birth,
                                   @ApiParam(name = "mobile",value = "手机号码",required = true) String mobile,
                                   @ApiParam(name = "degree",value = "学历",required = true) String degree,
                                   @ApiParam(name = "level",value = "职称",required = true) String level,
                                   @ApiParam(name = "idCard",value = "身份证",required = true) String idCard,
                                   @ApiParam(name = "realName",value = "真实姓名",required = true) String realName,
                                   @ApiParam(name = "collegeId",value = "所属系",required = true) String collegeId){
       //接受前端传入的值,并存入user对象
        User user = new User();
        user.setUsername(username);
        user.setSex(Integer.valueOf(sex));
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           date = sdf.parse(birth);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirth(date);
        user.setMobile(mobile);
        user.setIdCard(idCard);
        user.setFlag(Integer.valueOf(Constant.TEACHER_ROLE));
        user.setCollegeId(Integer.valueOf(collegeId));
        user.setRealName(realName);
        user.setDegree(degree);
        user.setLevel(level);
       RestResponse response =  userService.saveTeacher(user);
       return response;
    }


    /**
     * 由前端传入条件，获取教师列表
     * @return
     */
    @ApiOperation("添加学生")
    @RequestMapping(value = "/AddStudent",method =RequestMethod.POST)
    public RestResponse AddStudent(@ApiParam(name = "username",value = "工号",required = true) String username,
                                   @ApiParam(name = "sex",value = "性别 1 男 0女",required = true) String sex,
                                   @ApiParam(name = "birth",value = "出生年月") String birth,
                                   @ApiParam(name = "mobile",value = "手机号码",required = true) String mobile,
                                   @ApiParam(name = "collegeId",value = "院系",required = true) String collegeId,
                                   @ApiParam(name = "className",value = "班级名",required = true) String className,
                                   @ApiParam(name = "idCard",value = "身份证",required = true) String idCard,
                                   @ApiParam(name = "realName",value = "真实姓名",required = true) String realName
                                  ){
        //接受前端传入的值,并存入user对象
        User user = new User();
        user.setUsername(username);
        user.setSex(Integer.valueOf(sex));
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(birth);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirth(date);
        user.setMobile(mobile);

        user.setIdCard(idCard);
        user.setFlag(Integer.valueOf(Constant.STUDENT_ROLE));
        user.setCollegeId(Integer.valueOf(collegeId));
        user.setRealName(realName);
        Classs classs = new Classs();
        classs.setClassName(className);
       Classs cls = classService.getByIdOrName(classs);
        user.setClassId(cls.getId());
        RestResponse response =  userService.saveStudent(user);
        return response;
    }

    /**
     * 由前端传入条件，获取教师列表
     * @return
     */
    @ApiOperation("更新学生信息学生")
    @RequestMapping(value = "/UpdateStudent",method =RequestMethod.POST)
    public RestResponse UpdateStudent(@ApiParam(name = "id",value = "用户表id",required = true) String id,
                                   @ApiParam(name = "sex",value = "性别 1 男 0女",required = true) String sex,
                                   @ApiParam(name = "birth",value = "出生年月") String birth,
                                   @ApiParam(name = "mobile",value = "手机号码",required = true) String mobile,
                                   @ApiParam(name = "collegeId",value = "院系",required = true) String collegeId,
                                   @ApiParam(name = "className",value = "班级名",required = true) String className,
                                   @ApiParam(name = "realName",value = "真实姓名",required = true) String realName
    ){
        //接受前端传入的值,并存入user对象
        User user = new User();
        user.setSex(Integer.valueOf(sex));
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(birth);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirth(date);
        user.setMobile(mobile);
        user.setId(Integer.valueOf(id));
        user.setFlag(Integer.valueOf(Constant.STUDENT_ROLE));
        user.setCollegeId(Integer.valueOf(collegeId));
        user.setRealName(realName);
        Classs classs = new Classs();
        classs.setClassName(className);
        Classs cls = classService.getByIdOrName(classs);
        user.setClassId(cls.getId());
        RestResponse response =  userService.update(user);
        return response;
    }




    /**
     * 由前端传入条件，获取教师列表
     * @return
     */
    @ApiOperation("修改教师")
    @RequestMapping(value = "/UpdateTeacher",method =RequestMethod.POST)
    public RestResponse UpdateTeacher(
                                   @ApiParam(name = "id",value = "id",required = true) String id,
                                   @ApiParam(name = "sex",value = "性别 1 男 0女",required = true) String sex,
                                   @ApiParam(name = "birth",value = "出生年月") String birth,
                                   @ApiParam(name = "mobile",value = "手机号码",required = true) String mobile,
                                   @ApiParam(name = "degree",value = "学历",required = true) String degree,
                                   @ApiParam(name = "level",value = "职称",required = true) String level,
                                   @ApiParam(name = "realName",value = "真实姓名",required = true) String realName,
                                   @ApiParam(name = "collegeId",value = "所属系",required = true) String collegeId){
        //接受前端传入的值,并存入user对象
        User user = new User();
        user.setId(Integer.valueOf(id));
        user.setSex(Integer.valueOf(sex));
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(birth);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirth(date);
        user.setMobile(mobile);
        user.setFlag(Integer.valueOf(Constant.TEACHER_ROLE));
        user.setCollegeId(Integer.valueOf(collegeId));
        user.setRealName(realName);
        user.setDegree(degree);
        user.setLevel(level);
        RestResponse response =  userService.update(user);
        return response;
    }



    /**
     * 由前端传入条件，获取教师列表
     * @return
     */
    @ApiOperation("添加院系")
    @RequestMapping(value = "/AddCollege",method =RequestMethod.POST)
    public RestResponse AddCollege(
            @ApiParam(name = "deptName",value = "院系名称",required = true) String deptName,
            @ApiParam(name = "deptCode",value = "院系编码",required = true) String deptCode
            ){
        //接受前端传入的值,并存入dept对象
        Dept dept = new Dept();
        dept.setCreateDate(new Date());
        dept.setUpdateDate(new Date());
        dept.setDeptName(deptName);
        dept.setDeptCode(deptCode);
        int flag = deptService.judgeCollegeExists(dept);
        if (flag > 0){ //院系已存在
            return  RestResponse.error(RestCode.COLLEGE_EXISTED);
        }else { //插入数据库表中
           RestResponse response =  deptService.save(dept);
           if (response != null){
               return RestResponse.success(RestCode.OK);
           }else {
               return RestResponse.error(RestCode.UNKNOW_ERROR);
           }
        }
    }




    /**
     * 接受前端传入的id,并删除指定用户，此处我是用逻辑删除
     * @return
     */
    @ApiOperation("删除用户")
    @RequestMapping(value = "/delUserById",method = RequestMethod.POST)
    public RestResponse delUserById(@ApiParam(name = "userId",required = true,value = "用户表id") String userId){
      return   userService.deleteById(Integer.valueOf(userId));
    }


    /**
     * 接受前端传入的id,，此处我是用逻辑删除
     * @return
     */
    @ApiOperation("删除院系")
    @RequestMapping(value = "/delCollegeById",method = RequestMethod.POST)
    public RestResponse delCollegeById(@ApiParam(name = "id",required = true,value = "院系表id") String id){
         return   deptService.deleteCollegeById(Integer.valueOf(id));
    }



    /**
     * 修改院系信息
     * @return
     */
    @ApiOperation("修改院系信息")
    @RequestMapping(value = "/UpdateCollege",method =RequestMethod.POST)
    public RestResponse UpdateCollege(
            @ApiParam(name = "id",value = "院系表id",required = true) String id,
            @ApiParam(name = "deptName",value = "院系名称",required = true) String deptName,
            @ApiParam(name = "deptCode",value = "院系编码",required = true) String deptCode){
        //接受前端传入的值,并存入dept对象
        Dept dept = new Dept();
        dept.setDeptCode(deptCode);
        dept.setDeptName(deptName);
        dept.setId(Integer.valueOf(id));
        dept.setUpdateDate(new Date());
        RestResponse restResponse =  deptService.update(dept);
        return restResponse;
    }

    @ApiOperation("根据院系名称或编码检索")
    @RequestMapping(value = "/getCollegeByCondition",method = RequestMethod.GET)
    public RestResponse getCollegeByCondition(@ApiParam(value = "页码",required = true)@RequestParam String page, @ApiParam(value = "每页数据条数",required = true)@RequestParam String limit,@ApiParam(value = "前端传入的用户对象") Dept dept){
        //使用分页插件
        PageHelper.startPage(Integer.parseInt(page) , Integer.parseInt(limit));
        //根据相关条件搜索
        List<Dept> collegeList =  deptService.findPage(dept);
        //根据查询的数据列表，得到分页的结果对象
        RestResponse restResponse = null;
        List<Dept> list = Lists.newArrayList();
        if (collegeList.size() > 0){
            //用户名唯一
            PageInfo<Dept> pageList = new PageInfo<Dept>(collegeList);
            list  = pageList.getList();
            long count = pageList.getTotal();
            restResponse =   RestResponse.success(list,count);
        }else {
            restResponse =  RestResponse.noData(null);
        }
        return  restResponse;

    }


    @ApiOperation("根据id获取当前登录用户信息")
    @RequestMapping(value = "/getPersonInfo",method = RequestMethod.GET)
    public RestResponse getPersonInfo(@ApiParam(value = "用户id",required = true)@RequestParam String id, HttpServletRequest request){

        Integer Id = Integer.valueOf(id);
        User user = userService.getUserById(Id);
        if (user != null){
            user.setIP(getIpAddressUtils.getIpAddress(request));
            return RestResponse.success(user);
        }
        return RestResponse.error(RestCode.DATA_NOT_EXISTS);
    }



    /**
     * 修改个人信息
     * @return
     */
    @ApiOperation("修改个人信息")
    @RequestMapping(value = "/UpdatePersionInfo",method =RequestMethod.POST)
    public RestResponse UpdatePersionInfo(
            @ApiParam(name = "id",value = "用户表id",required = true) String id,
           @ApiParam(name = "username",value = "登录工号",required = false) String username,
            @ApiParam(name = "realName",value = "真实姓名",required = false) String realName,
            @ApiParam(name = "birth",value = "出生年月",required = false) String birth,
            @ApiParam(name = "sex",value = "出生年月",required = true) String sex){
        //接受前端传入的值,并存入dept对象
        User user = new User();
        if (username != null){
            user.setUsername(username);
        }
        if (realName != null){
            user.setRealName(realName);
        }

        if (birth != null){
            Date date = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.parse(birth);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            user.setBirth(date);
        }
        user.setId(Integer.valueOf(id));
        user.setSex(Integer.valueOf(sex));
        user.setUpdateDate(new Date());
        RestResponse restResponse =  userService.update(user);
        return restResponse;
    }


    /**
     * 由前端传入条件，加载班级列表
     * @return
     */
    @ApiOperation("加载班级列表")
    @RequestMapping(value = "/getAllClass",method = RequestMethod.GET)
    public RestResponse getAllClass(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit){
        Classs classs = new Classs();
        RestResponse result = classService.findClassList(classs,page,limit);
        return result;
    }

    /**
     * 由前端传入条件，加载院系列表
     * @return
     */
    @ApiOperation("获取所有院系列表")
    @RequestMapping(value = "/getCollegeList",method = RequestMethod.GET)
    public RestResponse getCollegeList(){
        Dept dept = new Dept();
          List<Dept>  deptList =  deptService.findPage(dept);
       return  RestResponse.success(deptList);
    }


    /**
     *
     * @return
     */
    @ApiOperation("添加班级")
    @RequestMapping(value = "/AddClass",method =RequestMethod.POST)
    public RestResponse AddClass(
            @ApiParam(name = "className",value = "班级名称",required = true) String className,
            @ApiParam(name = "classNo",value = "班级号",required = true) String classNo,
            @ApiParam(name = "collegeId",value = "所属院系",required = true) String collegeId,
            @ApiParam(name = "studentNum",value = "班级人数",required = true) String studentNum

    ){
        //接受前端传入的值,并存入class对象
        Classs classs = new Classs();
        classs.setCreateDate(new Date());
        classs.setUpdateDate(new Date());
        classs.setClassNo(classNo);
        classs.setCollegeId(Integer.valueOf(collegeId));
        classs.setStudentNum(Integer.valueOf(studentNum));
        //班级名称不能重复
        classs.setClassName(className);

        int flag = classService.judgeClassExists(classs);
        if (flag > 0){ //院系已存在
            return  RestResponse.error(RestCode.CLASS_EXISTED);
        }else { //插入数据库表中
            RestResponse response =  classService.save(classs);
            if (response != null){
                return RestResponse.success(RestCode.OK);
            }else {
                return RestResponse.error(RestCode.UNKNOW_ERROR);
            }
        }

    }


    @ApiOperation("修改班级信息")
    @RequestMapping(value = "/UpdateClass",method =RequestMethod.POST)
    public RestResponse UpdateClass(
            @ApiParam(name = "id",value = "id",required = true) String id,
            @ApiParam(name = "classNo",value = "班级号",required = true) String classNo,
            @ApiParam(name = "collegeId",value = "所属院系",required = true) String collegeId,
            @ApiParam(name = "studentNum",value = "班级人数",required = true) String studentNum
            ){

        Classs classs = new Classs();
        classs.setClassNo(classNo);
        classs.setStudentNum(Integer.valueOf(studentNum));
        classs.setUpdateDate(new Date());
        classs.setId(Integer.valueOf(id));
        classs.setCollegeId(Integer.valueOf(collegeId));
        RestResponse response =  classService.update(classs);
        return response;
    }

    /**
     * 接受前端传入的id,，此处我是用物理删除，不可恢复
     * @return
     */
    @ApiOperation("删除班级")
    @RequestMapping(value = "/delClassById",method = RequestMethod.POST)
    public RestResponse delClassById(@ApiParam(name = "id",required = true,value = "班级表id") String classId){
        return   classService.deleteClassById(Integer.valueOf(classId));
    }

    @ApiOperation("根据名称名称或班级号检索")
    @RequestMapping(value = "/getClassListByCondition",method = RequestMethod.GET)
    public RestResponse getClassListByCondition(@ApiParam(value = "页码",required = true)@RequestParam String page, @ApiParam(value = "每页数据条数",required = true)@RequestParam String limit,@ApiParam(value = "前端传入的搜索对象") Classs classs){

        //使用分页插件
        PageHelper.startPage(Integer.parseInt(page) , Integer.parseInt(limit));
        //根据相关条件搜索
        List<Classs> classList =  classService.findPage(classs);
        //根据查询的数据列表，得到分页的结果对象
        RestResponse restResponse = null;
        List<Classs> list = Lists.newArrayList();
        if (classList.size() > 0){
            PageInfo<Classs> pageList = new PageInfo<Classs>(classList);
            list  = pageList.getList();
            long count = pageList.getTotal();
            restResponse =   RestResponse.success(list,count);
        }else {
            restResponse =  RestResponse.noData(null);
        }
        return  restResponse;

    }

    /**
     * 由前端传入条件，加载班级列表
     * @return
     */
    @ApiOperation("加载课程列表")
    @RequestMapping(value = "/getAllCourse",method = RequestMethod.GET)
    public RestResponse getAllCourse(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit){
        Course course = new Course();
        RestResponse result = courseService.findCourseList(course,page,limit);
        return result;
    }

    /**
     * 由前端传入条件，加载院系列表
     * @return
     */
    @ApiOperation("获取所有课程类型列表")
    @RequestMapping(value = "/getCourseType",method = RequestMethod.GET)
    public RestResponse getCourseType(){
        CourseType courseType = new CourseType();
        List<CourseType>  courseTypeList =  courseTypeService.findList(courseType);
        return  RestResponse.success(courseTypeList);
    }


    /**
     *添加课程
     * @return
     */
    @ApiOperation("添加课程")
    @RequestMapping(value = "/AddCourse",method = RequestMethod.POST)
    public RestResponse AddCourse(@ApiParam(name = "courseName",value = "课程名称",required = true) String courseName,
                                  @ApiParam(name = "courseCode",value = "课程流水号",required = true) String courseCode,
                                  @ApiParam(name = "coursecourseTypeType",value = "课程类型",required = true) String courseType,
                                  @ApiParam(name = "type",value = "是否参与选课",required = true) String type,
                                  @ApiParam(name = "startTime",value = "课程时间",required = true) String startTime,
                                  @ApiParam(name = "address",value = "上课地点",required = true) String address,
                                  @ApiParam(name = "score",value = "课程学分",required = true) String score,
                                  @ApiParam(name = "courseLong",value = "学时",required = true) String courseLong){

            //判断课程流水号是否已经存在
             Course course = new Course();
             course.setCourseCode(Constant.COLLEGE_CODE+Constant.EDUOFFICE_CODE+courseType+courseCode);
             List<Course> list =   courseService.findList(course);
             if (!list.isEmpty()){
                 return  RestResponse.error(RestCode.COURSE_EXISTED);
             }
             Course ce = new Course(course.getCourseCode(),courseName,courseType,startTime,address,Integer.valueOf(score),
                     Integer.valueOf(courseLong),
                     new Date(),new Date(),Integer.valueOf(type));

           RestResponse restResponse =  courseService.save(ce);
            return  restResponse;
    }



    /**
     *添加课程
     * @return
     */
    @ApiOperation("修改课程")
    @RequestMapping(value = "/UpdateCourse",method = RequestMethod.POST)
    public RestResponse UpdateCourse(@ApiParam(name = "id",value = "id",required = true) String id,
                                  @ApiParam(name = "courseName",value = "课程名称",required = true) String courseName,
                                  @ApiParam(name = "courseType",value = "课程类型",required = true) String courseType,
                                  @ApiParam(name = "startTime",value = "课程时间",required = true) String startTime,
                                  @ApiParam(name = "address",value = "上课地点",required = true) String address,
                                  @ApiParam(name = "score",value = "课程学分",required = true) String score,
                                  @ApiParam(name = "courseLong",value = "学时",required = true) String courseLong){

        Course course = new Course();
        course.setCourseName(courseName);
        course.setStartTime(startTime);
        course.setScore(Integer.valueOf(score));
        course.setAddress(address);
        course.setCourseLong(Integer.valueOf(courseLong));
        course.setCourseType(courseType);
        course.setUpdateDate(new Date());
        course.setId(Integer.valueOf(id));

        return  courseService.update(course);
    }




    /**
     * 添加课程类型
     * @return
     */
    @ApiOperation("添加课程类型")
    @RequestMapping(value = "/AddCourseType",method =RequestMethod.POST)
    public RestResponse AddCourseType(
            @ApiParam(name = "name",value = "类型名称",required = true) String name
    ){
        //接受前端传入的值
        CourseType courseType = new CourseType();
        courseType.setName(name);
        int flag = courseTypeService.judgeNameExists(courseType);
        if (flag > 0){ //院系已存在
            return  RestResponse.error(RestCode.TYPE_EXISTED);
        }else { //插入数据库表中
            RestResponse response =  courseTypeService.save(courseType);
            if (response != null){
                return RestResponse.success(RestCode.OK);
            }else {
                return RestResponse.error(RestCode.UNKNOW_ERROR);
            }
        }
    }


    @ApiOperation("根据名称名称或班级号检索")
    @RequestMapping(value = "/getCourseListByCondition",method = RequestMethod.GET)
    public RestResponse getCourseListByCondition(@ApiParam(value = "页码",required = true)@RequestParam String page, @ApiParam(value = "每页数据条数",required = true)@RequestParam String limit,@ApiParam(value = "前端传入的搜索对象") Course course){

        //使用分页插件
        PageHelper.startPage(Integer.parseInt(page) , Integer.parseInt(limit));
        //根据相关条件搜索
        List<Course> courseList =  courseService.findPage(course);
        //根据查询的数据列表，得到分页的结果对象
        RestResponse restResponse = null;
        List<Course> list = Lists.newArrayList();
        if (courseList.size() > 0){
            PageInfo<Course> pageList = new PageInfo<Course>(courseList);
            list  = pageList.getList();
            long count = pageList.getTotal();
            restResponse =   RestResponse.success(list,count);
        }else {
            restResponse =  RestResponse.noData(null);
        }
        return  restResponse;

    }

    /**
     * 接受前端传入的id,，此处我是用物理删除
     * @return
     */
    @ApiOperation("删除课程")
    @RequestMapping(value = "/delCourseById",method = RequestMethod.POST)
    public RestResponse delCourseById(@ApiParam(name = "id",required = true,value = "课程表id") String courseId){
        return   courseService.deleteCourseById(Integer.valueOf(courseId));
    }


    /**
     * 排课课程列表
     * @return
     */
    @ApiOperation("获取所有的课程")
    @RequestMapping(value = "/getAllCourseList",method = RequestMethod.GET)
    public RestResponse getAllList(){
        Course course = new Course();
       List<Course> courseList = courseService.findList(course);
        return  RestResponse.success(courseList);
    }

    /**
     * 所有的教师，便于排课
     * @return
     */
    @ApiOperation("获取所有的教师")
    @RequestMapping(value = "/getAllTeacherList",method = RequestMethod.GET)
    public RestResponse getAllTeacherList(){
        User user = new User();
        user.setFlag(Integer.valueOf(Constant.TEACHER_ROLE));
        List<User> teacherList =  userService.findPage(user);
        return  RestResponse.success(teacherList);
    }

    /**
     * 所有的班级
     * @return
     */
    @ApiOperation("获取所有的班级")
    @RequestMapping(value = "/getClassList",method = RequestMethod.GET)
    public RestResponse getClassList(){
        Classs classs = new Classs();
        List<Classs> classList =  classService.findPage(classs);
        return  RestResponse.success(classList);
    }



    /**
     * 排课列表
     * @return
     */
    @ApiOperation("获取所有已排课列表")
    @RequestMapping(value = "/getCourseArrangeList",method = RequestMethod.GET)
    public RestResponse getCourseArrangeList(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit){
        CourseArrange courseArrange = new CourseArrange();
        RestResponse response = courseArrangeService.findCourseArrangeList(courseArrange,page,limit);
        return  response;
    }




    /**
     *添加课程安排
     * @return
     */
    @ApiOperation("添加课程安排")
    @RequestMapping(value = "/AddCourseArrange",method = RequestMethod.POST)
    public RestResponse AddCourseArrange(
                                  @ApiParam(name = "courseAddress",value = "上课地点",required = true) String courseAddress,
                                  @ApiParam(name = "courseCode",value = "课程流水号",required = true) String courseCode,
                                  @ApiParam(name = "username",value = "授课老师",required = true) String username,
                                  @ApiParam(name = "className",value = "授课班级",required = true) String className,
                                  @ApiParam(name = "deptName",value = "所属院系",required = true) String collegeName,
                                  @ApiParam(name = "week",value = "星期",required = true) String week,
                                  @ApiParam(name = "jieci",value = "节次",required = true) String jieci) {

        CourseArrange courseArrange = new CourseArrange();
        courseArrange.setClassName(className);
        courseArrange.setCourseAddress(courseAddress);
        courseArrange.setTeacherNo(username);
        courseArrange.setCourseCode(courseCode);
        courseArrange.setCollegeName(collegeName);
        courseArrange.setWeek(Integer.valueOf(week));
        courseArrange.setJieci(Integer.valueOf(jieci));
        //根据课程代码获取，课程名称

        Course course = new Course();
        course.setCourseCode(courseCode);
        Course ce = courseService.getByCodeOrId(course);
        if (ce != null) {
            courseArrange.setCourseName(ce.getCourseName());
        }

      return   courseArrangeService.save(courseArrange);
    }


    /**
     * 接受前端传入的id,，此处我是用物理删除
     * @return
     */
    @ApiOperation("删除排课信息")
    @RequestMapping(value = "/delCollegeArrangeById",method = RequestMethod.POST)
    public RestResponse delCollegeArrangeById(@ApiParam(name = "id",required = true,value = "排课表id") String CourseArrangeId){
        return  courseArrangeService.deleteCourseArrangeById(Integer.valueOf(CourseArrangeId));
    }

    /**
     * 接受前端信息，编辑
     * @return
     */
    @ApiOperation("编辑排课信息")
    @RequestMapping(value = "/UpdateCollegeArrange",method = RequestMethod.POST)
    public RestResponse UpdateCollegeArrange(@ApiParam(name = "id",value = "id",required = true) String id,
                                             @ApiParam(name = "courseAddress",value = "上课地点",required = true) String courseAddress,
                                             @ApiParam(name = "courseCode",value = "课程流水号",required = true) String courseCode,
                                             @ApiParam(name = "username",value = "授课老师",required = true) String username,
                                             @ApiParam(name = "className",value = "授课班级",required = true) String className,
                                             @ApiParam(name = "deptName",value = "所属院系",required = true) String collegeName){

        CourseArrange courseArrange = new CourseArrange();
        courseArrange.setId(Integer.valueOf(id));
        courseArrange.setClassName(className);
        courseArrange.setCourseAddress(courseAddress);
        courseArrange.setTeacherNo(username);
        courseArrange.setCourseCode(courseCode);
        courseArrange.setCollegeName(collegeName);
        //根据课程代码获取，课程名称

        Course course = new Course();
        course.setCourseCode(courseCode);
        Course ce = courseService.getByCodeOrId(course);
        if (ce != null) {
            courseArrange.setCourseName(ce.getCourseName());
        }

        return   courseArrangeService.update(courseArrange);
    }



    @ApiOperation("根据相关条件检索")
    @RequestMapping(value = "/getCourseArrangeListByCondition",method = RequestMethod.GET)
    public RestResponse getCourseArrangeListByCondition(@ApiParam(value = "页码",required = true)@RequestParam String page, @ApiParam(value = "每页数据条数",required = true)@RequestParam String limit,@ApiParam(value = "前端传入的搜索对象") CourseArrange courseArrange){

        //使用分页插件
        PageHelper.startPage(Integer.parseInt(page) , Integer.parseInt(limit));
        //根据相关条件搜索
        List<CourseArrange> courseArrangeList =  courseArrangeService.findPage(courseArrange);
        //根据查询的数据列表，得到分页的结果对象
        RestResponse restResponse = null;
        List<CourseArrange> list = Lists.newArrayList();
        if (courseArrangeList.size() > 0){
            PageInfo<CourseArrange> pageList = new PageInfo<CourseArrange>(courseArrangeList);
            list  = pageList.getList();
            long count = pageList.getTotal();
            restResponse =   RestResponse.success(list,count);
        }else {
            restResponse =  RestResponse.noData(null);
        }
        return  restResponse;

    }


    @ApiOperation("获取通知列表")
    @RequestMapping(value = "/getAllNotice",method = RequestMethod.GET)
    public RestResponse getAllNotice(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit){

        Notice notice = new Notice();
       return noticeService.findNoticeList(notice,page,limit);

    }

    @ApiOperation("获取通知详情")
    @RequestMapping(value = "/getNoticeDetail",method = RequestMethod.GET)
    public RestResponse getNoticeDetail(String id){

        if (StringUtils.isNotBlank(id)){
            Notice notice =  noticeService.get(Integer.valueOf(id));
            return RestResponse.success(notice);
        }
            return RestResponse.noData(null);
    }


    @ApiOperation("获取最新5条通知列表")
    @RequestMapping(value = "/getRecentNotice",method = RequestMethod.GET)
    public RestResponse getRecentNotice(){
        Notice notice = new Notice();
        return noticeService.findRecentNotice();

    }


    /**
     *添加课程安排
     * @return
     */
    @ApiOperation("发布公告")
    @RequestMapping(value = "/AddNotice",method = RequestMethod.POST)
    public RestResponse AddNotice(@ApiParam(name = "title",value = "标题",required = true) String title,
                                  @ApiParam(name = "content",value = "公告内容",required = true) String content) {

        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setCreateDate(new Date());
        notice.setUpdateDate(new Date());
        return  noticeService.save(notice);

    }


    /**
     * 接受前端传入的id,，此处我是用物理删除
     * @return
     */
    @ApiOperation("删除公告")
    @RequestMapping(value = "/delNoticeById",method = RequestMethod.POST)
    public RestResponse delNoticeById(@ApiParam(name = "noticeId",required = true,value = "通知表id") String noticeId){
        return  noticeService.deleteNoticeById(Integer.valueOf(noticeId));
    }

    @ApiOperation("编辑公告信息")
    @RequestMapping(value = "/UpdateNotice",method = RequestMethod.POST)
    public RestResponse UpdateNotice(@ApiParam(name = "id",required = true,value = "通知表id") String id,
                                     @ApiParam(name = "titele",required = true,value = "标题") String titele,
                                     @ApiParam(name = "content",required = true,value = "内容") String content){

        Notice notice = new Notice();
        if (StringUtils.isNotBlank(titele)){
            notice.setTitle(titele);
        }
        if (StringUtils.isNotBlank(content)){
            notice.setContent(content);
        }
        notice.setId(Integer.valueOf(id));

         return   noticeService.update(notice);

    }

    @ApiOperation("根据相关条件检索")
    @RequestMapping(value = "/getNoticeByCondition",method = RequestMethod.GET)
    public RestResponse getNoticeByCondition(@ApiParam(value = "页码",required = true)@RequestParam String page, @ApiParam(value = "每页数据条数",required = true)@RequestParam String limit,@ApiParam(value = "前端传入的搜索对象") Notice notice){

        //使用分页插件
        PageHelper.startPage(Integer.parseInt(page) , Integer.parseInt(limit));
        //根据相关条件搜索
        List<Notice> noticeList = noticeService.findPage(notice);
        //根据查询的数据列表，得到分页的结果对象
        RestResponse restResponse = null;
        List<Notice> list = Lists.newArrayList();
        if (noticeList.size() > 0){
            PageInfo<Notice> pageList = new PageInfo<Notice>(noticeList);
            list  = pageList.getList();
            long count = pageList.getTotal();
            restResponse =   RestResponse.success(list,count);
        }else {
            restResponse =  RestResponse.noData(null);
        }
        return  restResponse;

    }

    @ApiOperation("获取所有学生课程成绩列表")
    @RequestMapping(value = "/getAllStudentGrade",method = RequestMethod.GET)
    public RestResponse getAllStudentGrade(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit){

        /* List<StudentGradeVo> studentGradeVos = Lists.newArrayList();
        StudentGrade studentGrade = new StudentGrade();
      List<StudentGrade> studentGradeList =  studentGradeService.findPage(studentGrade);
                if (!studentGradeList.isEmpty()){
                    for(StudentGrade tmp : studentGradeList){
                        //获取学生信息
                        User student = userService.findUserbyName(tmp.getStuNo());
                        Course ce = new Course();
                        ce.setCourseCode(tmp.getCourseCode());
                        Course course = courseService.getByCodeOrId(ce);
                        //根据院系id获取院系名称
                        Dept  dept = deptService.get(student.getCollegeId());
                        //组织数剧
                        StudentGradeVo studentGradeVo = new StudentGradeVo();
                        studentGradeVo.setId(tmp.getId().toString());
                        studentGradeVo.setStuNo(student.getUsername());
                        studentGradeVo.setRealName(student.getRealName());
                        studentGradeVo.setCourseName(course.getCourseName());
                        studentGradeVo.setCourseCode(course.getCourseCode());
                        studentGradeVo.setCollegeName(dept.getDeptName());
                        studentGradeVo.setMarks(tmp.getMarks());
                        studentGradeVo.setMarks(tmp.getFlag());
                       *//* StudentGradeVo studentGradeVo = new StudentGradeVo(tmp.getId().toString(),tmp.getStuNo(),student.getRealName(),
                                course.getCourseName(),course.getCourseCode(),dept.getDeptName(),tmp.getMarks().toString(),tmp.getFlag().toString()
                        );*//*
                        studentGradeVos.add(studentGradeVo);
                    }
                    //分页初始化
                    Page<StudentGradeVo> pg = PageHelper.startPage(page, limit);
                    //传入list
                    PageInfo pageInfo = new PageInfo(studentGradeVos);
                    RestResponse restResponse = null;
                   return restResponse = PageUtils.StartPage(pageInfo);

            }
                return RestResponse.noData(null);*/
        //分页初始化
       List<StudentGrade> studentGradeList =  studentGradeService.findPage(new StudentGrade());
        Page<StudentGrade> pg = PageHelper.startPage(page, limit);
        //传入list
        PageInfo pageInfo = new PageInfo(studentGradeList);
        RestResponse restResponse = null;
        return restResponse = PageUtils.StartPage(pageInfo);
    }



    @ApiOperation("管理员后台打分")
    @RequestMapping(value = "/MakeGrade",method = RequestMethod.POST)
    public RestResponse MakeGrade(@ApiParam(name = "id",required = true,value = "成绩表id") String id,
                                     @ApiParam(name = "titele",required = true,value = "分数") String marks
                                     ){

        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setId(Integer.valueOf(id));
        studentGrade.setMarks(marks);
        studentGrade.setFlag(Constant.SCOREED_FLAG);

         return    studentGradeService.update(studentGrade);

    }


    @ApiOperation("根据相关条件检索")
    @RequestMapping(value = "/getStudentGradeByCondition",method = RequestMethod.GET)
    public RestResponse getStudentGradeByCondition(@ApiParam(value = "页码",required = true)@RequestParam String page, @ApiParam(value = "每页数据条数",required = true)@RequestParam String limit,@ApiParam(value = "前端传入的搜索对象") StudentGrade studentGrade){

        //使用分页插件
        PageHelper.startPage(Integer.parseInt(page) , Integer.parseInt(limit));
        //根据相关条件搜索
        List<StudentGrade> studentGradeList = studentGradeService.findPage(studentGrade);
        //根据查询的数据列表，得到分页的结果对象
        RestResponse restResponse = null;
        List<StudentGrade> list = Lists.newArrayList();
        if (studentGradeList.size() > 0){
            PageInfo<StudentGrade> pageList = new PageInfo<StudentGrade>(studentGradeList);
            list  = pageList.getList();
            long count = pageList.getTotal();
            restResponse =   RestResponse.success(list,count);
        }else {
            restResponse =  RestResponse.noData(null);
        }
        return  restResponse;

    }


    @ApiOperation("获取所有事务")
    @RequestMapping(value = "/getAllTran",method = RequestMethod.GET)
    public RestResponse getAllTran(@ApiParam(value = "页码",required = true)@RequestParam String page, @ApiParam(value = "每页数据条数",required = true)@RequestParam String limit){

        //使用分页插件
        PageHelper.startPage(Integer.parseInt(page) , Integer.parseInt(limit));
        //根据相关条件搜索
        List<Tran> tranList = tranService.findList(new Tran());
        //根据查询的数据列表，得到分页的结果对象
        RestResponse restResponse = null;
        List<Tran> list = Lists.newArrayList();
        if (tranList.size() > 0){
            PageInfo<Tran> pageList = new PageInfo<Tran>(tranList);
            list  = pageList.getList();
            long count = pageList.getTotal();
            restResponse =   RestResponse.success(list,count);
        }else {
            restResponse =  RestResponse.noData(null);
        }
        return  restResponse;

    }



    @ApiOperation("同意申请")
    @RequestMapping(value = "/passTran",method = RequestMethod.POST)
    public RestResponse passTran(@ApiParam(name = "id",required = true,value = "id") String id){

        Tran tran = new Tran();
        tran.setId(Integer.valueOf(id));
        tran.setStatus("1");
        return   tranService.update(tran);

    }

    @ApiOperation("拒绝申请")
    @RequestMapping(value = "/rejectTran",method = RequestMethod.POST)
    public RestResponse rejectTran(@ApiParam(name = "id",required = true,value = "id") String id){

        Tran tran = new Tran();
        tran.setId(Integer.valueOf(id));
        tran.setStatus("2");
        return   tranService.update(tran);

    }


    @ApiOperation("删除事务")
    @RequestMapping(value = "/deleteTran",method = RequestMethod.POST)
    public RestResponse deleteTran(@ApiParam(name = "id",required = true,value = "id") String id){
        return  tranService.delete(Integer.valueOf(id));

    }




    @ApiOperation("获取课题列表")
    @RequestMapping(value = "/getAllSelect",method = RequestMethod.GET)
    public RestResponse getAllSelect(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit){

        Select select = new Select();
        return selectService.findSelectList(select,page,limit);

    }



    @ApiOperation("发布课题")
    @RequestMapping(value = "/AddSelect",method = RequestMethod.POST)
    public RestResponse AddSelect(@ApiParam(name = "name",value = "名称",required = true) String name,
                                  @ApiParam(name = "content",value = "内容",required = true) String content) {

        Select select = new Select();
        select.setName(name);

     List<Select>  list =  selectService.findList(select);
     if (!list.isEmpty()){
         return RestResponse.error(RestCode.SELECT_EXISETED);
    }
        Select select1 = new Select();
        select1.setName(name);
        select1.setContent(content);

        return  selectService.save(select1);

    }



    /**
     * 接受前端传入的id,，此处我是用物理删除
     * @return
     */
    @ApiOperation("删除课题")
    @RequestMapping(value = "/delSelectById",method = RequestMethod.POST)
    public RestResponse delSelectById(@ApiParam(name = "id",required = true,value = "id") String id){
        return  selectService.deleteSelectById(Integer.valueOf(id));
    }

    @ApiOperation("编辑课题")
    @RequestMapping(value = "/UpdateSelect",method = RequestMethod.POST)
    public RestResponse UpdateSelect(@ApiParam(name = "id",required = true,value = "通知表id") String id,
                                     @ApiParam(name = "name",required = true,value = "名称") String name,
                                     @ApiParam(name = "content",required = true,value = "内容") String content){

        Select select = new Select();
        select.setName(name);
        select.setContent(content);
        select.setId(Integer.valueOf(id));

        return   selectService.update(select);

    }



    @ApiOperation("根据课题查询")
    @RequestMapping(value = "/getSelectByCondition",method = RequestMethod.GET)
    public RestResponse getSelectByCondition(@ApiParam(value = "页码",required = true)@RequestParam String page, @ApiParam(value = "每页数据条数",required = true)@RequestParam String limit,@ApiParam(value = "前端传入的搜索对象") Select select){

        //使用分页插件
        PageHelper.startPage(Integer.parseInt(page) , Integer.parseInt(limit));
        //根据相关条件搜索
        List<Select> noticeList = selectService.findPage(select);
        //根据查询的数据列表，得到分页的结果对象
        RestResponse restResponse = null;
        List<Select> list = Lists.newArrayList();
        if (noticeList.size() > 0){
            PageInfo<Select> pageList = new PageInfo<Select>(noticeList);
            list  = pageList.getList();
            long count = pageList.getTotal();
            restResponse =   RestResponse.success(list,count);
        }else {
            restResponse =  RestResponse.noData(null);
        }
        return  restResponse;

    }




    @ApiOperation("获取所有选题列表")
    @RequestMapping(value = "/getAllSelectList",method = RequestMethod.GET)
    public RestResponse getAllSelectList(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit){


        List<SelectList> list = selectListService.findList(new SelectList());
        if (list.size() > 0){
            PageHelper.startPage(page,limit);
            PageInfo pageInfo = new PageInfo(list);
            RestResponse restResponse = PageUtils.StartPage(pageInfo);
            return restResponse;
        }
        return  RestResponse.noData(null);

    }


    @ApiOperation("根据条件检索")
    @RequestMapping(value = "/getSelectListByCondition",method = RequestMethod.GET)
    public RestResponse getSelectListByCondition(@ApiParam(value = "页码",required = true)@RequestParam String page, @ApiParam(value = "每页数据条数",required = true)@RequestParam String limit,@ApiParam(value = "前端传入的搜索对象") SelectList selectList){

        //使用分页插件
        PageHelper.startPage(Integer.parseInt(page) , Integer.parseInt(limit));
        //根据相关条件搜索
        List<SelectList> noticeList = selectListService.findList(selectList);
        //根据查询的数据列表，得到分页的结果对象
        RestResponse restResponse = null;
        List<SelectList> list = Lists.newArrayList();
        if (noticeList.size() > 0){
            PageInfo<SelectList> pageList = new PageInfo<SelectList>(noticeList);
            list  = pageList.getList();
            long count = pageList.getTotal();
            restResponse =   RestResponse.success(list,count);
        }else {
            restResponse =  RestResponse.noData(null);
        }
        return  restResponse;

    }


    /**
     * 接受前端传入的id,，此处我是用物理删除
     * @return
     */
    @ApiOperation("删除学生选的课题")
    @RequestMapping(value = "/delSelectListById",method = RequestMethod.POST)
    public RestResponse delSelectListById(@ApiParam(name = "id",required = true,value = "id") String id){
        return  selectListService.deleteById(Integer.valueOf(id));
    }










}
