package com.edu.manger.service.impl;

import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dao.CourseTypeMapper;
import com.edu.manger.entry.CourseType;
import com.edu.manger.service.CourseTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: CourseTypeServiceImpl
 * Description:
 * date: 2020/3/23 13:33
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Service
public class CourseTypeServiceImpl implements CourseTypeService {

    @Resource
    public CourseTypeMapper courseTypeMapper;

    @Transactional
    @Override
    public RestResponse save(CourseType courseType) {
       int flag =  courseTypeMapper.insert(courseType);
       if (flag > 0){
           return RestResponse.success(RestCode.OK);
       }
        return RestResponse.error(RestCode.UNKNOW_ERROR);
    }

    @Override
    public List<CourseType> findList(CourseType courseType) {
        courseTypeMapper.findList(courseType);
        return courseTypeMapper.findList(courseType);
    }

    @Override
    public int judgeNameExists(CourseType courseType) {
     List<CourseType> list =  courseTypeMapper.judgeNameExists(courseType);
        return list.size();
    }
}
