package com.edu.manger.utils;

import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: PageUtils
 * Description:
 * date: 2020/3/19 18:29
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public class PageUtils {
    /**
     * 分页方法使用
     * @param pageInfo
     * @return
     */
    public static RestResponse StartPage(PageInfo pageInfo){
        RestResponse response = new RestResponse();

        List list= new ArrayList();
        if (pageInfo != null){
            //获取数据列表
            list = pageInfo.getList();
            //获取数据总数
            long count=pageInfo.getTotal();
            response.setCount(count);
            response.setCode(RestCode.OK.code);
            response.setMsg(RestCode.OK.msg);
            response.setData(list);
            return  response;
        }else {
          return   RestResponse.noData(list);
        }
    }
}
