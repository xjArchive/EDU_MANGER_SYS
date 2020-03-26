package com.edu.manger.dao;

import com.edu.manger.entry.StudentGrade;

import java.util.List;

/**
 * ClassName: StudentGradeMapper
 * Description:
 * date: 2020/3/24 17:14
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public interface StudentGradeMapper {

    int insert(StudentGrade studentGrade);


    public List<StudentGrade> findStudentGradeList(StudentGrade studentGrade);

    public List<StudentGrade> findList(StudentGrade studentGrade);

    public int delete(Integer id);

    public int update(StudentGrade studentGrade);

    public StudentGrade get(Integer id);

    public int deleteAllByStu(String stuNo);


}
