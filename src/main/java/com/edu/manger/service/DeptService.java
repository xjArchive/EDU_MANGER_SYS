package com.edu.manger.service;

import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.Dept;

import java.util.List;

/**
 * ClassName: DeptService
 * Description:
 * date: 2020/3/19 15:00
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public interface DeptService {

    public RestResponse findCollegeList(Dept dept,Integer page,Integer limit);

    public RestResponse deleteCollegeById(Integer id);

    //判断院系不能重复
    public int judgeCollegeExists(Dept dept);

    //判断院系不能重复
    public RestResponse save(Dept dept);

    //修改信息
    public RestResponse update(Dept dept);

    public List<Dept> findPage(Dept dept);

    public Dept get(Integer id);
}
