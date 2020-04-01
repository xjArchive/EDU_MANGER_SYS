package com.edu.manger.service.impl;

import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.SelectListMapper;
import com.edu.manger.dao.SelectMapper;
import com.edu.manger.entry.Select;
import com.edu.manger.entry.SelectList;
import com.edu.manger.service.SelectService;
import com.edu.manger.utils.PageUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * ClassName:
 * Description:
 * date: 2020/3/23 11:31
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Service
public class SelectServiceImpl implements SelectService {

    @Resource
    public SelectMapper selectMapper;

    @Resource
    public SelectListMapper selectListMapper;


    @Override
    public RestResponse findSelectList(Select select, Integer page, Integer limit) {
        List<Select> selects =   selectMapper.findSelectList(select);
        //分页初始化
        Page<Select> pg = PageHelper.startPage(page, limit);
        //传入list
        PageInfo pageInfo = new PageInfo(selects);
        RestResponse restResponse = null;
        restResponse = PageUtils.StartPage(pageInfo);
        return  restResponse;
    }

    @Override
    @Transactional
    public RestResponse save(Select select) {

        if (select != null){
            int flag =  selectMapper.insert(select);
            if (flag > 0){
                return RestResponse.success(RestCode.OK);
            }
        }
        return RestResponse.error(RestCode.UNKNOW_ERROR);
    }

    @Override
    public List<Select> findList(Select select) {
        List<Select> noticeList =  selectMapper.findList(select);
        return noticeList;
    }

    @Override
    @Transactional
    public RestResponse update(Select select) {
        //首先判断更改的值是否与数据库表数据重复

        int flag =  selectMapper.update(select);
        if (flag > 0){
            return RestResponse.success(RestCode.OK);
        }
        return  RestResponse.error(RestCode.UNKNOW_ERROR);
    }

    @Override
    public List<Select> findPage(Select select) {
        List<Select> list =   selectMapper.findList(select);
        return list;
    }

    @Override
    public RestResponse deleteSelectById(Integer id) {
        int a = selectMapper.delete(id);
        if (a > 0 ){
            //删除成功
            return   RestResponse.success(RestCode.OK);
        }else{
            return RestResponse.error(RestCode.DELETE_FAIL);
        }
    }

    @Override
    @Transactional
    public Select get(Integer id) {
        return  selectMapper.get(id);
    }


    /**
     *
     * 将选题表中该题目改为已被选，同时添加学生选题列表中
     * @param id
     * @param username
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResponse SelectAndSave(Integer id, String username) {
        //找到该选题
        if (id != null && StringUtils.isNotBlank(username)){
            Select select = selectMapper.get(id);
            select.setStatus(1);
            selectMapper.update(select);
            //同时在选题列表中添加学生选题信息
            SelectList selectList = new SelectList();
            selectList.setUsername(username);
            selectList.setCreateDate(new Date());
            selectList.setSelectId(select.getId());
            selectList.setName(select.getName());
            selectListMapper.insert(selectList);
            return RestResponse.success(RestCode.OK);
        }

        return RestResponse.noData(null);
    }
}
