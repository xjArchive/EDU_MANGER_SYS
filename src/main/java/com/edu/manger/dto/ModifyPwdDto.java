package com.edu.manger.dto;

/**
 * ClassName: ModifyPwd
 * Description:
 * date: 2020/3/28 15:38
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public class ModifyPwdDto {

    private String mobile;
    private  String username;
    private String password;
    private String idCard;
    private String code;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
