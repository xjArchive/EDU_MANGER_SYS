package com.edu.manger.service.impl;

import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.SelectListMapper;
import com.edu.manger.entry.SelectList;
import com.edu.manger.service.SelectListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class SelectListServiceImpl implements SelectListService {

    @Resource
    public SelectListMapper selectListMapper;

    @Transactional
    @Override
    public RestResponse save(SelectList selectList) {

        if (selectList != null){
          int flag =  selectListMapper.insert(selectList);
          if (flag > 0){
              return RestResponse.success(RestCode.OK);
          }
        }
        return RestResponse.error(RestCode.UNKNOW_ERROR);

    }

    @Override
    public List<SelectList> findList(SelectList selectList) {
      List<SelectList> noticeList =  selectListMapper.findList(selectList);
        return noticeList;
    }

    @Transactional
    @Override
    public RestResponse update(SelectList selectList) {
        //首先判断更改的值是否与数据库表数据重复

      int flag =  selectListMapper.update(selectList);
      if (flag > 0){
          return RestResponse.success(RestCode.OK);
      }
      return  RestResponse.error(RestCode.UNKNOW_ERROR);
    }

    @Override
    @Transactional
    public RestResponse deleteById(Integer id) {
        int a = selectListMapper.delete(id);
        if (a > 0 ){
            //删除成功
            return   RestResponse.success(RestCode.OK);
        }else{
            return RestResponse.error(RestCode.DELETE_FAIL);
        }
    }

    @Override
    public SelectList get(Integer id) {
        return  selectListMapper.get(id);
    }
}
