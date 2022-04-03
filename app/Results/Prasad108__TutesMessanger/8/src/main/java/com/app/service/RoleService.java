package com.app.service;
 import java.util.List;
import com.app.pojo.Role;
public interface RoleService {


public List<Role> getall()
;

public Role edit(int id)
;

public Role find(int id)
;

public void create(Role role)
;

public void update(Role role)
;

public Role findByName(String name)
;

public void delet(int id)
;

}