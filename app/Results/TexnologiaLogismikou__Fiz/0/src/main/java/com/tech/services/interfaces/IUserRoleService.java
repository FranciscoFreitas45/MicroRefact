package com.tech.services.interfaces;
 import com.tech.models.entities.user.UserRole;
import java.util.List;
public interface IUserRoleService {


public void deleteUserRole(UserRole userRole)
;

public List<UserRole> getUserRolesByRoles(String role)
;

public void addUserRole(UserRole userRole)
;

public void modifyUserRole(UserRole newRole)
;

public List<UserRole> getAllUserRoles()
;

public String getRoleByUserID(Long userid)
;

}