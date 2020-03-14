package com.edu.manger.dao;

import com.edu.manger.entry.Role;

public interface RoleMapper {
   public  int insert(Role role);

   public Role select(Role role);

   public  int update(Role role);

   public void delete(Integer id);

}