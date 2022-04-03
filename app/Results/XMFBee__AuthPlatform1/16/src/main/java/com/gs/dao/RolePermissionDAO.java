package com.gs.dao;
 import com.gs.bean.Permission;
import com.gs.bean.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RolePermissionDAO extends BaseDAO<String, RolePermission>{


public int insertList(String roleId,List<String> permissionIds)
;

public int addPermission(String roleId,String permissionId)
;

public List<String> queryAllPermissionByRoleName(String roleName)
;

public int removePermission(String roleId,String permissionId)
;

public List<Permission> queryPermissions(String roleId,String roleStatus)
;

public List<Permission> queryPermissionById(Integer id)
;

}