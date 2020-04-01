package com.edu.manger.service;

import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.Notice;
import com.edu.manger.entry.Select;

import java.util.List;

/**
 * ClassName: NoticeService
 * Description:
 * date: 2020/3/23 11:29
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */

public interface SelectService {

    public RestResponse findSelectList(Select select, Integer page, Integer limit);

    //保存（需判断课程代码是否重复）
    public RestResponse save(Select select);

    public List<Select> findList(Select select);

    //修改信息
    public RestResponse update(Select select);

    public List<Select> findPage(Select select);

    public RestResponse deleteSelectById(Integer id);


    public Select get(Integer id);

    public RestResponse SelectAndSave(Integer id,String username);
}
