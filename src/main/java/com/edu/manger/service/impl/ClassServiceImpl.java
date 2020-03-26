package com.edu.manger.service.impl;

import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.ClassMapper;
import com.edu.manger.entry.Classs;
import com.edu.manger.service.ClassService;
import com.edu.manger.utils.PageUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: ClassServiceImpl
 * Description:
 * date: 2020/3/22 15:24
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */@Service
public class ClassServiceImpl implements ClassService {

    @Resource
    private ClassMapper classMapper;


    @Override
    public RestResponse findClassList(Classs classs, Integer page, Integer limit) {
        List<Classs> classList = classMapper.findClassList(classs);
        //分页初始化
        Page<Classs> pg = PageHelper.startPage(page, limit);
        //传入list
        PageInfo pageInfo = new PageInfo(classList);
        RestResponse restResponse = null;
        restResponse = PageUtils.StartPage(pageInfo);

       /* if (!collegeList.isEmpty()){

            restResponse = RestResponse.success(collegeList,Long.parseLong(collegeList.size()+""));
        }*/
        return 	restResponse;
    }

    @Override
    @Transactional
    public RestResponse deleteClassById(Integer id) {
        int a = classMapper.delete(id);
        if (a > 0 ){
            //删除成功
            return   RestResponse.success(RestCode.OK);
        }else{
            return RestResponse.error(RestCode.DELETE_FAIL);
        }
    }

    @Override
    public int judgeClassExists(Classs classs) {
        List<Classs> deptList = classMapper.judgeClassExists(classs);
        return deptList.size();
    }

    @Override
    public RestResponse save(Classs classs) {
        int flag = classMapper.insert(classs);
        if (flag > 0){
            return  RestResponse.success();
        }
        return null;
    }

    @Override
    @Transactional
    public RestResponse update(Classs classs) {
        //首先判断更改的值是否与数据库表数据重复
        int flag =  classMapper.update(classs);
        if (flag > 0){
            return RestResponse.success(RestCode.OK);
        }
        return  RestResponse.error(RestCode.UNKNOW_ERROR);
    }

    @Override
    public List<Classs> findPage(Classs classs) {
        List<Classs> list =   classMapper.findClassList(classs);
        return list;

    }

    @Override
    public Classs getByIdOrName(Classs classs) {
        if (classs != null){
            return classMapper.get(classs);
        }
        return null;
    }

}
