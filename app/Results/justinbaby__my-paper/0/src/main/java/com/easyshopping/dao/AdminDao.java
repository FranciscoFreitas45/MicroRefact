package com.easyshopping.dao;
 import com.easyshopping.entity.Admin;
public interface AdminDao extends BaseDao<Admin, Long>{


public boolean usernameExists(String username)
;

public Admin findByUsername(String username)
;

}