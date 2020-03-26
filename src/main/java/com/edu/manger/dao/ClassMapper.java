package com.edu.manger.dao;

import com.edu.manger.entry.Classs;
import com.edu.manger.entry.Dept;

import java.util.List;

/**
 * ClassName: ClassMapper
 * Description:
 * date: 2020/3/22 15:02
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public interface ClassMapper {
    int insert(Classs classs);

    int insertSelective(Classs classs);

    public List<Classs> findClassList(Classs classs);

    public int delete(Integer id);

    //判断院系不能重复
    public List<Classs> judgeClassExists(Classs classs);

    public int update(Classs classs);

    public Classs get(Classs classs);

}
