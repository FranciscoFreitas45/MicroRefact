package com.lingxiang2014.dao;
 import com.lingxiang2014.entity.Admin;
public interface AdminDao extends BaseDao<Admin, Long>{


public boolean usernameExists(String username)
;

public Admin findByUsername(String username)
;

}