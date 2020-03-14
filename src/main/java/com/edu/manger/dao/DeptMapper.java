package com.edu.manger.dao;

import com.edu.manger.entry.Dept;

public interface DeptMapper {
    int insert(Dept record);

    int insertSelective(Dept record);
}