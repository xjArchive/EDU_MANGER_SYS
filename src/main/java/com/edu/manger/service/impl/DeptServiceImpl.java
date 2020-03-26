package com.edu.manger.service.impl;

import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.DeptMapper;
import com.edu.manger.dao.UserMapper;
import com.edu.manger.entry.Dept;
import com.edu.manger.service.DeptService;
import com.edu.manger.utils.PageUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: DeptServiceImpl
 * Description:
 * date: 2020/3/19 15:01
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public RestResponse findCollegeList(Dept dept,Integer page,Integer limit) {

        List<Dept> collegeList = deptMapper.findCollegeList(dept);
        //分页初始化
        Page<Dept> pg = PageHelper.startPage(page, limit);
        //传入list
        PageInfo pageInfo = new PageInfo(collegeList);
        RestResponse restResponse = null;
        restResponse = PageUtils.StartPage(pageInfo);

       /* if (!collegeList.isEmpty()){

            restResponse = RestResponse.success(collegeList,Long.parseLong(collegeList.size()+""));
        }*/
        return 	restResponse;
    }

    @Override
    @Transactional
    public RestResponse deleteCollegeById(Integer id) {
       int a = deptMapper.delete(id);
        if (a > 0 ){
            //删除成功
         return   RestResponse.success(RestCode.OK);
        }else{
            return RestResponse.error(RestCode.DELETE_FAIL);
        }
    }

    @Override
    public int judgeCollegeExists(Dept dept) {
       List<Dept> deptList = deptMapper.judgeCollegeExists(dept);
       return deptList.size();
    }

    @Override
    @Transactional
    public RestResponse save(Dept dept) {
        int flag = deptMapper.insert(dept);
        if (flag > 0){
            return  RestResponse.success();
        }
        return null;
    }

    @Override
    @Transactional
    public RestResponse update(Dept dept) {
        //首先判断更改的值是否与数据库表数据重复
        List<Dept> depts = deptMapper.judgeCollegeExists(dept);
        if (depts.size() > 0){ //院系名称或编码冲突
            return RestResponse.error(RestCode.COLLEGE_EXISTED);
        }else {
            int update = deptMapper.update(dept);
            if (update > 0){
                return  RestResponse.success(RestCode.OK);
            }else {
                return RestResponse.error(RestCode.UNKNOW_ERROR);
            }
        }
    }

    @Override
    public List<Dept> findPage(Dept dept) {
        List<Dept> list =   deptMapper.findCollegeList(dept);
        return list;
    }

    @Override
    public Dept get(Integer id) {
        return  deptMapper.get(id);
    }
}
