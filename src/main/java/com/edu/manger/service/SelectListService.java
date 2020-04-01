package com.edu.manger.service;

import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.SelectList;

import java.util.List;

public interface SelectListService {

    public RestResponse save(SelectList selectList);

    public List<SelectList> findList(SelectList selectList);

    //修改信息
    public RestResponse update(SelectList selectList);

    public RestResponse deleteById(Integer id);


    public SelectList get(Integer id);
}
