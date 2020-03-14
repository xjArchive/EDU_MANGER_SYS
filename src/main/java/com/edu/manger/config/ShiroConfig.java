package com.edu.manger.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ShiroConfig
 * Description:
 * date: 2020/3/11 12:02
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("security") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置内置过滤器，实现权限相关的拦截器   anon:  无需认证   authc: 必须认证  user:  rememberMe访问等
        Map<String,String> map = new HashMap<>();
        map.put("/js/**","anon");
        map.put("/avatars/**","anon");
        map.put("/css/**","anon");
        map.put("/font/**","anon");
        map.put("/fonts/**","anon");
        map.put("/images/**","anon");
        map.put("/img/**","anon");
        map.put("/layui/**","anon");
        map.put("/login","anon");
        map.put("/swagger/**","anon");
        map.put("/druid/**","anon");
        map.put("/**","anon");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return  shiroFilterFactoryBean;
    }

    /**
     * 创建DeafaultWebSecurityManger
     */

    @Bean(name = "security")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm") UserRealm realm){

        DefaultWebSecurityManager SecurityManager = new DefaultWebSecurityManager();
        SecurityManager.setRealm(realm);
        return SecurityManager;
    }

    /**
     * 创建realm
     */
    @Bean("realm")
    public UserRealm getRealm(){
        return  new UserRealm();
    }






}
