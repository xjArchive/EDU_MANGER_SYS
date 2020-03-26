package com.edu.manger.entry;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ClassName: CourseType
 * Description:
 * date: 2020/3/23 12:01
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseType {

    private Integer id;
    private String name;

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
}
