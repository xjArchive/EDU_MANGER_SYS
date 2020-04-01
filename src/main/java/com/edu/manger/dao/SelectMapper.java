package com.edu.manger.dao;

import com.edu.manger.entry.Select;

import java.util.List;

/**
 *
 */
public interface SelectMapper {
    int insert(Select select);

    public List<Select> findSelectList(Select select);

    public List<Select> findList(Select select);

    public int delete(Integer id);

    public int update(Select select);

    public Select get(Integer id);

}
