package com.edu.manger.entry;


import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: Permission
 * Description:
 * date: 2020/3/12 11:32
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
public class Permission implements Serializable {

    private Integer id;
    private String name;
    private String perms;
    private String url;
    private Date createDate;
    private Date updateDate;
    private String remarks;
    private String DelFlag;

    public Permission() {
    }

    public Permission(Integer id, String name, String perms, String url, Date createDate, Date updateDate, String remarks, String delFlag) {
        this.id = id;
        this.name = name;
        this.perms = perms;
        this.url = url;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.remarks = remarks;
        DelFlag = delFlag;
    }

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

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return DelFlag;
    }

    public void setDelFlag(String delFlag) {
        DelFlag = delFlag;
    }
}
