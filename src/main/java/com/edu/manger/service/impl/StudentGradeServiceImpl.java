package com.edu.manger.service.impl;

import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.NoticeMapper;
import com.edu.manger.dao.StudentGradeMapper;
import com.edu.manger.entry.Notice;
import com.edu.manger.entry.StudentGrade;
import com.edu.manger.service.NoticeService;
import com.edu.manger.service.StudentGradeService;
import com.edu.manger.utils.PageUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName:
 * Description:
 * date: 2020/3/23 11:31
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Service
public class StudentGradeServiceImpl implements StudentGradeService {

    @Resource
    public StudentGradeMapper studentGradeMapper;

    @Override
    public RestResponse findStudentGradeList(StudentGrade studentGrade, Integer page, Integer limit) {

       List<StudentGrade> studentGradelist =   studentGradeMapper.findStudentGradeList(studentGrade);
        //分页初始化
        Page<StudentGrade> pg = PageHelper.startPage(page, limit);
        //传入list
        PageInfo pageInfo = new PageInfo(studentGradelist);
        RestResponse restResponse = null;
        restResponse = PageUtils.StartPage(pageInfo);
       return  restResponse;
    }

    @Transactional
    @Override
    public RestResponse save(StudentGrade studentGrade) {

        if (studentGrade != null){
          int flag =  studentGradeMapper.insert(studentGrade);
          if (flag > 0){
              return RestResponse.success(RestCode.OK);
          }
        }
        return RestResponse.error(RestCode.UNKNOW_ERROR);

    }

    @Override
    public List<StudentGrade> findList(StudentGrade studentGrade) {
      List<StudentGrade> studentGradeList =  studentGradeMapper.findList(studentGrade);
        return studentGradeList;
    }

    @Transactional
    @Override
    public RestResponse update(StudentGrade studentGrade) {
        //首先判断更改的值是否与数据库表数据重复

      int flag =  studentGradeMapper.update(studentGrade);
      if (flag > 0){
          return RestResponse.success(RestCode.OK);
      }
      return  RestResponse.error(RestCode.UNKNOW_ERROR);
    }

    @Override
    public List<StudentGrade> findPage(StudentGrade studentGrade) {
        List<StudentGrade> list =   studentGradeMapper.findList(studentGrade);
        return list;
    }


    @Override
    public StudentGrade get(Integer id) {
        return  studentGradeMapper.get(id);
    }
}
