package com.edu.manger.dao;

import com.edu.manger.entry.SelectList;

import java.util.List;

/**
 * ClassName: SelectListMapper
 * Description:
 * date: 2020/3/31 21:11
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public interface SelectListMapper {

    int insert(SelectList selectList);

    public List<SelectList> findList(SelectList selectList);

    public int delete(Integer id);

    public int update(SelectList selectList);

    public SelectList get(Integer id);


}
