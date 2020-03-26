package com.edu.manger.config;

import com.edu.manger.entry.Role;
import com.edu.manger.entry.User;
import com.edu.manger.entry.UserRole;
import com.edu.manger.service.RoleService;
import com.edu.manger.service.UserRoleService;
import com.edu.manger.service.UserService;
import com.edu.manger.utils.PasswordMd5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

/**
 * ClassName: UserRealm
 * Description:   用于shiro连接数据库
 * date: 2020/3/11 13:05
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public class UserRealm  extends AuthorizingRealm {


    @Autowired
    public UserService userService;

    @Autowired
    public UserRoleService userRoleService;

    @Autowired
    public RoleService roleService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权逻辑");

        //查询数据库，根据用户名其角色权限信息
      User user = (User) SecurityUtils.getSubject().getPrincipal();
        User dbuser = userService.findUserbyName(user.getUsername());
        //根据角色id获取当前是什么角色
       UserRole userRole = userRoleService.getRoleByUserId(dbuser.getId());
      Role db_role = new Role();
       if (userRole != null){
          Integer roleId =  userRole.getRoleId();
           Role role = new Role();
           role.setId(roleId);
           db_role = roleService.select(role);
       }
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //增加资源的授权字符串
        Set<String> r = new HashSet<String>();
        if (db_role != null) {
            r.add(db_role.getDescription());
            info.setRoles(r);
        }
        return info;
    }


    /**
     * 认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");
        // 加这一步的目的是在Post请求的时候会先认证
        if (authenticationToken.getPrincipal() == null){
            return  null;
        }
        //获取用户名，并查找数据库
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //判断用户
        User user = userService.findUserbyName(token.getUsername());
        if (user == null) {
            return null;
        }
        //判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(PasswordMd5Utils.salt),this.getClass().getName());
    }

}
