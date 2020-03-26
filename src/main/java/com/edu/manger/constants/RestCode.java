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
    DATA_NOT_EXISTS(200, "暂无数据"),
    USER_EXISTED(10101, "用户已存在，请重新填写"),
    IDCARD_REPEATE(10102, "身份证号已被使用，重新填写"),
    UPDATE_FAIL(10103,"抱歉,修改失败"),
    DELETE_FAIL(10104,"抱歉,删除失败"),
    COLLEGE_EXISTED(10105,"抱歉,院系已存在，请重新填写"),
    CLASS_EXISTED(10106,"抱歉,班级已存在，请重新填写班级名称"),
    TYPE_EXISTED(10107,"抱歉,课程类型已存在，请重新填写"),
    COURSE_EXISTED(10108,"抱歉,课程已存在，请重新填写"),
    VERICODE_ERROR(10109,"抱歉,验证码有误"),
    VERICODE_ISEMPTY(101011,"请先获取验证码"),
    MOBILE_ERROR(101010,"抱歉,该手机号码未绑定"),;
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
