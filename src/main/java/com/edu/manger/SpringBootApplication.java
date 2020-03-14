package com.edu.manger;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * ClassName: SpringBootApplication
 * Description:
 * date: 2020/3/10 18:47
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */

@org.springframework.boot.autoconfigure.SpringBootApplication
@MapperScan("com.edu.manger.dao")
public class SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class,args);
    }
}
