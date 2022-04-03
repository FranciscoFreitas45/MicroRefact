package com.gs.Interface;
public interface RolePermissionService {

   public List<Permission> queryPermissions(String roleId,String roleStatus);
   public int insertList(String roleId,List<String> permissionIds);
   public int removePermission(String roleId,String permissionId);
   public int addPermission(String roleId,String permissionId);
}