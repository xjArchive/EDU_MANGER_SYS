package com.edu.manger.constants;

/**
 * ClassName: RestCode
 * Description:
 * date: 2020/3/11 14:32
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public enum RestCode {
    OK(200, "OK"),
    UNKNOW_ERROR(500, "服务异常"),
    WRONG_PAGE(10100, "页码不存在"),
            ;

    RestCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code;
    public String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
