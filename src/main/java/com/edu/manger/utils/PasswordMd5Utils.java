package com.edu.manger.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * ClassName: PasswordMd5Utils
 * Description:
 * date: 2020/3/20 14:42
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public class PasswordMd5Utils {
    public static final String hashAlgorithName = "MD5"; //加密方式
    public static final int hashIterations = 1024;// 加密次数
    public static final String  salt = "徐进";// 自定义salt值
    /**
     *
     * @param password 明文
     * @return  加密之后的密码
     */
    public static String passwordByMd5(String password){
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);

        Object pwd = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        return  pwd.toString();
    }

  /*  public static void main(String[] args) {
      Object obj = passwordByMd5("123456");
        System.out.println(obj.toString());
    }*/
}
