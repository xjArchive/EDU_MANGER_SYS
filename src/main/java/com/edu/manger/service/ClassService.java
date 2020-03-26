package com.edu.manger.service;

import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.Classs;

import java.util.List;

/**
 * ClassName: ClassService
 * Description:
 * date: 2020/3/22 15:23
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public interface ClassService {

    public RestResponse findClassList(Classs classs, Integer page, Integer limit);

    public RestResponse deleteClassById(Integer id);

    //判断院系不能重复
    public int judgeClassExists(Classs classs);

    //判断院系不能重复
    public RestResponse save(Classs classs);

    //修改信息
    public RestResponse update(Classs classs);

    public List<Classs> findPage(Classs classs);

    public Classs getByIdOrName(Classs classs);


}
