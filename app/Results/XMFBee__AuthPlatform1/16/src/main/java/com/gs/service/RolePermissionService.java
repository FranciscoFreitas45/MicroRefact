package com.gs.service;
 import com.gs.bean.Permission;
import org.apache.ibatis.annotations.Param;
import java.util.Collection;
import java.util.List;
public interface RolePermissionService {


public int insertList(String roleId,List<String> permissionIds)
;

public int addPermission(String roleId,String permissionId)
;

public Collection<org.apache.shiro.authz.Permission> queryAllPermissionByRoleName(String roleName)
;

public int removePermission(String roleId,String permissionId)
;

public List<Permission> queryPermissions(String roleId,String roleStatus)
;

public List<Permission> queryPermissionById(Integer id)
;

}