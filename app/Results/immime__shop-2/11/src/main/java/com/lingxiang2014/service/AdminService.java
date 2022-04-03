package com.lingxiang2014.service;
 import java.util.List;
import com.lingxiang2014.entity.Admin;
public interface AdminService extends BaseService<Admin, Long>{


public boolean usernameExists(String username)
;

public Admin findByUsername(String username)
;

public String getCurrentUsername()
;

public List<String> findAuthorities(Long id)
;

public boolean isAuthenticated()
;

public Admin getCurrent()
;

}