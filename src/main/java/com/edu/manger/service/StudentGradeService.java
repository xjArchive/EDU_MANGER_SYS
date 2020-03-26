package com.edu.manger.service;

import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.StudentGrade;

import java.util.List;

/**
 * ClassName: NoticeService
 * Description:
 * date: 2020/3/23 11:29
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */

public interface StudentGradeService {

    public RestResponse findStudentGradeList(StudentGrade StudentGrade, Integer page, Integer limit);

    //保存（需判断课程代码是否重复）
    public RestResponse save(StudentGrade StudentGrade);

    public List<StudentGrade> findList(StudentGrade StudentGrade);

    //修改信息
    public RestResponse update(StudentGrade StudentGrade);

    public List<StudentGrade> findPage(StudentGrade StudentGrade);


    public StudentGrade get(Integer id);
}
