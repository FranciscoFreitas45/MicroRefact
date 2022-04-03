package com.gs.shiro;
 import com.gs.service.RolePermissionService;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Collection;
@Component(value = "rolePermissionResolver")
public class MyRolePermissionResolver implements RolePermissionResolver{

@Resource
 private  RolePermissionService rolePermissionService;


@Override
public Collection<Permission> resolvePermissionsInRole(String roleName){
    return rolePermissionService.queryAllPermissionByRoleName(roleName);
}


}