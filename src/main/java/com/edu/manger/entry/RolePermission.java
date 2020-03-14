package com.edu.manger.entry;

import io.swagger.models.auth.In;

import java.io.Serializable;

public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer roleId;
    private Integer permissionId;

    public RolePermission() {
    }

    public RolePermission(Integer id, Integer roleId, Integer permissionId) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}