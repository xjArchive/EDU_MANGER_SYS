package com.edu.manger.controller;

import com.edu.manger.constants.Constant;
import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dto.StudentGradeDto;
import com.edu.manger.entry.*;
import com.edu.manger.service.*;
import com.edu.manger.vo.StudentGradeVo;
import com.google.common.collect.Lists;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * ClassName: TeacherController
 * Description:
 * date: 2020/3/26 21:16
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/student")
@Api(value = "学生权限控制器", description = "学生权限控制器")
@CrossOrigin
public class StudentController {

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

    @Autowired
    private TranService tranService;

    @Autowired
    private CourseTypeService courseTypeService;

    @Autowired
    private SelectService selectService;

    @Autowired
    private SelectListService selectListService;

    @ApiOperation("我的课程")
    @RequestMapping(value = "/getMyCourse",method = RequestMethod.GET)
    public RestResponse getAllStudent(String studentNo){


            //获取用户id
    User user =  userService.getUserById(Integer.valueOf(studentNo));
    if (user != null){
     //获取班级id
     Classs cls = new Classs();
     cls.setId(user.getClassId());
    Classs classs =  classService.getByIdOrName(cls);
    //根据班级名称查询课程安排表的课程安排
     CourseArrange courseArrange = new CourseArrange();
     courseArrange.setClassName(classs.getClassName());
     List<CourseArrange> courseList = courseArrangeService.findPage(courseArrange);
     courseList.forEach(tmp ->{
        tmp.setTeacherName(userService.findUserbyName(tmp.getTeacherNo()).getRealName());
     });
     if (!courseList.isEmpty()){
       return RestResponse.success(courseList);
     }
    }
       return RestResponse.noData(null);
    }



 @ApiOperation("学生成绩")
 @RequestMapping(value = "/getMyGrade",method = RequestMethod.GET)
 public RestResponse getMyGrade( StudentGradeDto studentGradeDto){


 User user = userService.findUserbyName(studentGradeDto.getStuNo());

  StudentGrade studentGrade = new StudentGrade();
  studentGrade.setStuNo(studentGradeDto.getStuNo());
  studentGrade.setFlag(Constant.SCOREED_FLAG);
  //获取已评分的课程
 List<StudentGrade>  studentGradeList =  studentGradeService.findList(studentGrade);
  List<StudentGradeVo>  studentGradeVoList =  Lists.newArrayList();
  studentGradeList.forEach(tmp->{
      StudentGradeVo studentGradeVo = new StudentGradeVo();
      studentGradeVo.setCourseName(tmp.getCourseName());
      studentGradeVo.setCourseCode(tmp.getCourseCode());
      studentGradeVo.setMarks(tmp.getMarks());

      CourseArrange courseArrange = new CourseArrange();
      courseArrange.setCourseCode(tmp.getCourseCode());

      Classs classs = new Classs();
      classs.setId(user.getClassId());
      courseArrange.setClassName(classService.getByIdOrName(classs).getClassName());

     String teacherName =  courseArrangeService.findPage(courseArrange).get(0).getTeacherName();

      studentGradeVo.setTeacherName(teacherName);
      Course course = new Course();
      course.setCourseCode(tmp.getCourseCode());
      String type =   courseService.getByCodeOrId(course).getCourseType();
      String typeName = courseTypeService.get(Integer.valueOf(type)).getName();
      studentGradeVo.setCourseType(typeName);
      if (Integer.valueOf(tmp.getMarks()) < 60){
       studentGradeVo.setPass("0");
      }else {
       studentGradeVo.setPass("1");
      }
   studentGradeVoList.add(studentGradeVo);
  });

  if (studentGradeVoList.size() > 0){
   //分页(不需要)
   /*PageHelper.startPage(page,limit);
   PageInfo pageInfo = new PageInfo(studentGradeVoList);
   return  PageUtils.StartPage(pageInfo);*/
   return RestResponse.success(studentGradeVoList);
  }
  return RestResponse.noData(null);
 }



    @ApiOperation("所有的选课列表")
    @RequestMapping(value = "/AllSelectCourse",method = RequestMethod.GET)
    public RestResponse AllSelectCourse(){

       // Course course = new Course();
       // course.setType(1);  //选修标识

        CourseArrange courseArrange = new CourseArrange();
        //获取所有已排课的列表
        //申明list存储所有的选课
        List<CourseArrange> list = Lists.newArrayList();
      List<CourseArrange> courseArrangeList =  courseArrangeService.findPage(new CourseArrange());
   if(courseArrangeList.size()>0){ //从所有排课表中帅选出是参加选课的课程（type=1）
       courseArrangeList.forEach(tmp -> {
           Course course = new Course();
           course.setCourseCode(tmp.getCourseCode());
           //获取该课程
          Course ce =  courseService.getByCodeOrId(course);
          if (ce.getType() == 1){  //是选课类型，存储进来
              list.add(tmp);
          }
       });
       return  RestResponse.success(list,Long.valueOf(list.size()));
   }
        return RestResponse.noData(null);
    }



    @ApiOperation("学生进行选课")
    @RequestMapping(value = "/SelectCourse",method = RequestMethod.POST)
    public RestResponse SelectCourse(@ApiParam(name = "studentNo") @RequestParam String studentNo,
                                     @ApiParam(name = "courseId") @RequestParam String courseId){


        if (StringUtils.isNotBlank(studentNo) && StringUtils.isNotBlank(courseId)){

            CourseArrange courseArrange = courseArrangeService.get(Integer.valueOf(courseId));

          User user = userService.findUserbyName(studentNo);

          //判断该学生是否已经选择了该课程
            StudentGrade sg = new StudentGrade();
            sg.setCourseCode(courseArrange.getCourseCode());
            sg.setStuNo(studentNo);
            sg.setCollegeId(user.getCollegeId().toString());
         List<StudentGrade>  studentGradeList =  studentGradeService.findPage(sg);
         if (!studentGradeList.isEmpty()){
             return RestResponse.error(RestCode.REPEATE_SELECT_COURSE);
         }
            StudentGrade studentGrade = new StudentGrade();
            studentGrade.setFlag(Constant.NOT_SCORE_FLAG);
            studentGrade.setStuNo(studentNo);
            studentGrade.setType("1");
            studentGrade.setCourseCode(courseArrange.getCourseCode());
            studentGrade.setCollegeId(user.getCollegeId().toString());

           return studentGradeService.save(studentGrade);
        }


        return RestResponse.noData(null);
    }


    @ApiOperation("我的选课")
    @RequestMapping(value = "/mySelect",method = RequestMethod.GET)
    public RestResponse mySelect(@ApiParam(name = "studentNo") @RequestParam String studentNo){


        if (StringUtils.isNotBlank(studentNo)){
            StudentGrade studentGrade = new StudentGrade();
            studentGrade.setStuNo(studentNo);
            studentGrade.setType("1");
           List<StudentGrade> studentGradeList = studentGradeService.findPage(studentGrade);
           return RestResponse.success(studentGradeList);
        }
        return RestResponse.noData(null);
    }


    @ApiOperation("多图片文件上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public RestResponse uploadImgs(MultipartFile file, HttpServletRequest request){
        String desFilePath = "";  //文件上传路径
        String oriName = "";  //文件原始名
        Map<String, String> dataMap = new HashMap<>();
        try {
            // 1.获取原文件名
            oriName = file.getOriginalFilename();
            // 2.获取原文件图片后缀，以最后的.作为截取(.jpg)
            String extName = oriName.substring(oriName.lastIndexOf("."));
            // 3.生成自定义的新文件名，这里以UUID.jpg|png|xxx作为格式（可选操作，也可以不自定义新文件名）
            String uuid = UUID.randomUUID().toString();
            String newName = uuid + extName;
            // 4.获取要保存的路径文件夹
            String realPath = request.getRealPath("uploadImgs/");
            // 5.保存图片
            desFilePath = realPath + "\\" + newName;
            File desFile = new File(desFilePath);
            file.transferTo(desFile);
            System.out.println(desFilePath);
            // 6.返回保存结果信息
            dataMap = new HashMap<>();
            dataMap.put("src",newName);
            return   RestResponse.success(dataMap);
        } catch (IllegalStateException e) {
           // imgResult.setCode(1);
            System.out.println(desFilePath + "图片保存失败");
            return RestResponse.error(RestCode.UNKNOW_ERROR);
        } catch (IOException e) {
            return RestResponse.error(RestCode.UNKNOW_ERROR);
        }

    }


    @ApiOperation("保存事务")
    @RequestMapping(value = "/saveTran",method = RequestMethod.POST)
    public RestResponse saveTran(String username,String title,String content,String img){

      Tran tran = new Tran();
      tran.setCreateBy(username);
        tran.setContent(content);
        tran.setTitle(title);
        tran.setCreateDate(new Date());
        tran.setUpdateDate(new Date());
        if (StringUtils.isNotBlank(img)){
            tran.setImg(img);
        }
       return  tranService.save(tran);
    }



    @ApiOperation("保存事务")
    @RequestMapping(value = "/getMyTran",method = RequestMethod.GET)
    public RestResponse getMyTran(String username){

        Tran tran = new Tran();
        tran.setCreateBy(username);
      List<Tran> tranList =  tranService.findList(tran);
        if (!tranList.isEmpty()){
            return  RestResponse.success(tranList);
        }
        return RestResponse.noData(null);
    }


    @ApiOperation("所有的选题列表")
    @RequestMapping(value = "/AllSelectList",method = RequestMethod.GET)
    public RestResponse AllSelectList(@ApiParam(value = "页码",required = true)@RequestParam Integer page, @ApiParam(value = "每页数据条数",required = true)@RequestParam Integer limit){

        Select select = new Select();
        select.setStatus(0);

        RestResponse result = selectService.findSelectList(select, page, limit);
        return result;
    }

    @ApiOperation("选题")
    @RequestMapping(value = "/SelectList",method = RequestMethod.POST)
    public RestResponse SelectList(String id,String username){

        //判断该学生是否已选题
        SelectList selectList = new SelectList();
        selectList.setUsername(username);

        List<SelectList> list = selectListService.findList(selectList);
        if (list.size() > 0){
         return    RestResponse.error(RestCode.SELECT_EXISETED);
        }

        return selectService.SelectAndSave(Integer.valueOf(id),username);
    }







}
