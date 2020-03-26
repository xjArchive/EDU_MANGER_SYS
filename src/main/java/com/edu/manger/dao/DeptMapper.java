package com.edu.manger.dao;

import com.edu.manger.entry.Dept;

import java.util.List;

public interface DeptMapper {
    int insert(Dept dept);

    int insertSelective(Dept record);

    public List<Dept> findCollegeList(Dept dept);

    public int delete(Integer id);

    //判断院系不能重复
    public List<Dept> judgeCollegeExists(Dept dept);

    public int update(Dept dept);

    public Dept get(Integer id);
}