package com.ushahidi.swiftriver.core.api.dao;
 import com.ushahidi.swiftriver.core.model.Role;
public interface RoleDao extends GenericDao<Role>{


public Role findByName(String name)
;

}