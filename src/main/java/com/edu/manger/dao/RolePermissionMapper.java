package com.edu.manger.dao;

import com.edu.manger.entry.RolePermission;

public interface RolePermissionMapper {
    int insert(RolePermission rolePermission);

    RolePermission get(RolePermission rolePermission);
}