package com.softserve.edu.Resources.dao;
 import com.softserve.edu.Resources.entity.Role;
import java.util.List;
public interface RoleDAO {


public List<Role> getAllRoles()
;

public Role updateRole(Role role)
;

public Role findByName(String name)
;

public void delete(Role role)
;

}