package com.edu.manger.service.impl;

import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.TranMapper;
import com.edu.manger.entry.Tran;
import com.edu.manger.service.TranService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TranServiceImpl implements TranService {

    @Resource
    public TranMapper tranMapper;


    @Transactional
    @Override
    public RestResponse save(Tran tran) {

        if (tran != null){
          int flag =  tranMapper.insert(tran);
          if (flag > 0){
              return RestResponse.success(RestCode.OK);
          }
        }
        return RestResponse.error(RestCode.UNKNOW_ERROR);

    }

    @Override
    public List<Tran> findList(Tran tran) {
      List<Tran> trans =  tranMapper.findList(tran);
        return trans;
    }


    @Override
    public Tran get(Integer id) {
        return  tranMapper.get(id);
    }

    @Override
    public RestResponse update(Tran tran) {
        //首先判断更改的值是否与数据库表数据重复

        int flag =  tranMapper.update(tran);
        if (flag > 0){
            return RestResponse.success(RestCode.OK);
        }
        return  RestResponse.error(RestCode.UNKNOW_ERROR);
    }

    @Override
    public RestResponse delete(Integer id) {
        int a = tranMapper.delete(id);
        if (a > 0 ){
            //删除成功
            return   RestResponse.success(RestCode.OK);
        }else{
            return RestResponse.error(RestCode.DELETE_FAIL);
        }
    }
}
