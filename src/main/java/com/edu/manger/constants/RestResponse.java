package com.edu.manger.constants;

/**
 * ClassName: RestResponse
 * Description:
 * date: 2020/3/11 14:32
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public class RestResponse <T> {
    private int code;
    private String msg;
    private T data;
    private Long count;


    /**
     * 分别提供返回成功和失败的不同的方法
     * 也就是说返回的数据形式是RestResponse所包含的
     * d第一个T ：泛型方法的标示，没有实际的意义
     * 第二个返回的数据类型的一种规范
          *这也就是所谓的工厂模式的应用,
     */



    public static <T> RestResponse<T> success() {
        return new RestResponse<>();
    }

    public static <T> RestResponse<T> success(RestCode restCode) {
        RestResponse<T> restResponse = new RestResponse<>(restCode.code, restCode.msg);
        return restResponse;
    }


    public static <T> RestResponse<T> success(T data,Long count) {
        RestResponse restResponse = new RestResponse(RestCode.OK.code,RestCode.OK.msg,data,count);
        restResponse.setData(data);
        return restResponse;
    }

    public static <T> RestResponse<T> success(T data) {
        RestResponse restResponse = new RestResponse(RestCode.OK.code,RestCode.OK.msg,data);
        restResponse.setData(data);
        return restResponse;
    }

    public RestResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> RestResponse<T> noData(T data) {
        RestResponse restResponse = new RestResponse(RestCode.DATA_NOT_EXISTS.code,RestCode.DATA_NOT_EXISTS.msg,data,Long.parseLong(0+""));
        restResponse.setData(data);
        return restResponse;
    }

    public static <T> RestResponse<T> error(RestCode restCode) {
        RestResponse<T> restResponse = new RestResponse<>(restCode.code, restCode.msg);
        return restResponse;
    }

    public RestResponse() {
        //默认会调用有参的构造函数,默认是成功的
        //this(RestCode.OK.code, RestCode.OK.msg);
    }

    public RestResponse(int code, String msg, T data,Long count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    public RestResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public RestResponse(T data) {
        this.data = data;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
