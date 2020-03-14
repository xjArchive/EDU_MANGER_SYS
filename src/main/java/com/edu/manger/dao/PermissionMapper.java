package com.edu.manger.dao;

import com.edu.manger.entry.Permission;
import com.edu.manger.entry.User;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: PermissionMapper
 * Description:
 * date: 2020/3/12 11:36
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public interface PermissionMapper {

    public  int insert(Permission permission);

    public Permission get(Integer id);

    public int delete(Integer id);

    public int update(Integer id);
}
