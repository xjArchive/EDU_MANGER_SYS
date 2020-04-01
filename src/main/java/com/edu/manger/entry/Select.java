package com.edu.manger.entry;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * ClassName: Select
 * Description:
 * date: 2020/3/31 16:22
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Select {

    private Integer id;
    private String name;
    private String content;
    private Integer status;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

