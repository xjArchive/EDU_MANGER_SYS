package com.edu.manger.controller;

import com.edu.manger.constants.Constant;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dto.StudentGradeDto;
import com.edu.manger.entry.*;
import com.edu.manger.service.*;
import com.edu.manger.utils.PageUtils;
import com.edu.manger.vo.StudentGradeVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * ClassName: TeacherController
 * Description:
 * date: 2020/3/26 21:16
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/teacher")
@Api(value = "教师权限控制器", description = "教师权限控制器")
@CrossOrigin
public class TeacherController {

    @Autowired
   private UserService userService;

    @Autowired
    private CourseArrangeService courseArrangeService;

    @Autowired
    private StudentGradeService studentGradeService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClassService classService;



    @ApiOperation("加载学生列表")
    @RequestMapping(value = "/getMyStudent",method = RequestMethod.GET)
    public RestResponse getAllStudent(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit,
                                       String teacherNo){


        if (StringUtils.isNotBlank(teacherNo)){
          return   userService.getTeacherStus(teacherNo,page,limit);
        }
        return RestResponse.noData(null);
    }


    /**
     * 排课列表
     * @return
     */
    @ApiOperation("获取当前教师用户所有的授课列表")
    @RequestMapping(value = "/getMyCourssArrange",method = RequestMethod.GET)
    public RestResponse getMyCourssArrange(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit,
                                           @ApiParam(value = "教师工号",required = true)@RequestParam String teacherNo){
        CourseArrange courseArrange = new CourseArrange();
        courseArrange.setTeacherNo(teacherNo);
       RestResponse restResponse = courseArrangeService.findCourseArrangeList(courseArrange,page,limit);
        return  restResponse;
    }

    @ApiOperation("根据条件查询所授课程列表")
    @RequestMapping(value = "/getMyCourseListByCondition",method = RequestMethod.GET)
    public RestResponse getMyCourseListByCondition(@ApiParam(value = "页码",required = true)@RequestParam Integer page,
                                                   @ApiParam(value = "数据条数",required = true)@RequestParam Integer limit,
                                                   @ApiParam(value = "封装的查询对象",required = true) CourseArrange courseArrange){
        //使用分页插件
       PageHelper.startPage(page , limit);
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


    @ApiOperation("根据条件条件搜索")
    @RequestMapping(value = "/getStudentByCondition",method = RequestMethod.GET)
    public RestResponse getStudentByCondition(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit,@ApiParam(value = "前端传入的用户对象") User user,
                                              String teacherNo){

        return  userService.getTeacherStusByCondition(teacherNo,page,limit,user);

    }


    @ApiOperation("获取该教师的学生课程成绩列表")
    @RequestMapping(value = "/getMyStudentGrade",method = RequestMethod.GET)
    public RestResponse getMyStudentGrade(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit,String teacherNo){


        //首先获取该教师所属的所有学生
       RestResponse restResponse =  userService.getTeacherStus(teacherNo,page,limit);
        if (restResponse.getData() == null){
            return RestResponse.noData(null);
        }
      List<User> userList = (List<User>) restResponse.getData();
        List<StudentGrade> stuList = Lists.newArrayList();
        userList.forEach(tmp->{
            StudentGrade studentGrade = new StudentGrade();
            studentGrade.setStuNo(tmp.getUsername());
         List<StudentGrade> studentGradeList =  studentGradeService.findList(studentGrade);
            stuList.addAll(studentGradeList);
        });

        List<StudentGradeVo> studentGradeVos = Lists.newArrayList();

        stuList.forEach(tmp -> {
            //组织数剧返回前端
            StudentGradeVo studentGradeVo = new StudentGradeVo();

            studentGradeVo.setMarks(tmp.getMarks());
            studentGradeVo.setStuNo(tmp.getStuNo());
            studentGradeVo.setFlag(tmp.getFlag());
            studentGradeVo.setCollegeName(deptService.get(Integer.valueOf(tmp.getCollegeId())).getDeptName());
            studentGradeVo.setCourseCode(tmp.getCourseCode());
            studentGradeVo.setCourseName(tmp.getCourseName());
            studentGradeVo.setId(tmp.getId().toString());

            //获取班级名称
            Integer classId =  userService.findUserbyName(tmp.getStuNo()).getClassId();
            Classs classs = new Classs();
            classs.setId(classId);
            studentGradeVo.setClassName(classService.getByIdOrName(classs).getClassName());

            studentGradeVo.setRealName(tmp.getRealName());
            studentGradeVos.add(studentGradeVo);
        });
        //分页展示
        PageHelper.startPage(page,limit);
        PageInfo pageInfo = new PageInfo(studentGradeVos);
        RestResponse restResponse1 = PageUtils.StartPage(pageInfo);
        return restResponse1;
    }




    @ApiOperation("根据条件获取该教师的学生课程成绩列表")
    @RequestMapping(value = "/getStudentGradeByCondition",method = RequestMethod.GET)
    public RestResponse getStudentGradeByCondition(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit, StudentGradeDto studentGradeDto){


        //首先获取该教师所属的所有学生
        RestResponse restResponse =  userService.getTeacherStus(studentGradeDto.getTeacherNo(),page,limit);
        if (restResponse.getData() == null){
            return RestResponse.noData(null);
        }
        List<User> userList = (List<User>) restResponse.getData();
        List<StudentGrade> stuList = Lists.newArrayList();
        userList.forEach(tmp->{
            StudentGrade studentGrade = new StudentGrade();
            studentGrade.setStuNo(tmp.getUsername());
            //加入搜索条件
            if (StringUtils.isNotBlank(studentGradeDto.getStuNo())){
                studentGrade.setStuNo(studentGradeDto.getStuNo());
            }
            if (StringUtils.isNotBlank(studentGradeDto.getCourseCode())){
                studentGrade.setCourseCode(studentGradeDto.getCourseCode());
            }
            if (StringUtils.isNotBlank(studentGradeDto.getFlag())){
                studentGrade.setFlag(studentGradeDto.getFlag());
            }
            List<StudentGrade> studentGradeList =  studentGradeService.findList(studentGrade);
            //遍历已经查询到的list,根据className,来移除不符合条件的
            if (StringUtils.isNotBlank(studentGradeDto.getClassName())){
                Iterator<StudentGrade> iterable =  studentGradeList.iterator();
                while (iterable.hasNext()){
                    StudentGrade sg = iterable.next();
                    User user = userService.findUserbyName(sg.getStuNo());
                    Classs classs = new Classs();
                    classs.setId(user.getClassId());
                    Classs cls =  classService.getByIdOrName(classs);
                    if (!studentGradeDto.getClassName().equals(cls.getClassName())){
                        iterable.remove();
                    }
                }
                /*for (StudentGrade sg: studentGradeList){  for循环遍历加remove   有坑
                    User user = userService.findUserbyName(sg.getStuNo());
                    Classs classs = new Classs();
                    classs.setId(user.getClassId());
                    Classs cls =  classService.getByIdOrName(classs);
                    if (!studentGradeDto.getClassName().equals(cls.getClassName())){
                        studentGradeList.remove(sg);
                    }

                }*/
            }

            stuList.addAll(studentGradeList);
        });

        List<StudentGradeVo> studentGradeVos = Lists.newArrayList();

        stuList.forEach(tmp -> {
            //组织数剧返回前端
            StudentGradeVo studentGradeVo = new StudentGradeVo();

            studentGradeVo.setMarks(tmp.getMarks());
            studentGradeVo.setStuNo(tmp.getStuNo());
            studentGradeVo.setFlag(tmp.getFlag());
            studentGradeVo.setCollegeName(deptService.get(Integer.valueOf(tmp.getCollegeId())).getDeptName());
            studentGradeVo.setCourseCode(tmp.getCourseCode());
            studentGradeVo.setCourseName(tmp.getCourseName());
            studentGradeVo.setId(tmp.getId().toString());

            //获取班级名称
            Integer classId =  userService.findUserbyName(tmp.getStuNo()).getClassId();
            Classs classs = new Classs();
            classs.setId(classId);
            studentGradeVo.setClassName(classService.getByIdOrName(classs).getClassName());

            studentGradeVo.setRealName(tmp.getRealName());
            studentGradeVos.add(studentGradeVo);
        });
        //分页展示
        PageHelper.startPage(page,limit);
        PageInfo pageInfo = new PageInfo(studentGradeVos);
        RestResponse restResponse1 = PageUtils.StartPage(pageInfo);
        return restResponse1;
    }



    @ApiOperation("教师打分")
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


}
