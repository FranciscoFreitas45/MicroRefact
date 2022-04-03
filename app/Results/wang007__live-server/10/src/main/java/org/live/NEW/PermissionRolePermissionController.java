package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.sys.entity.Permission;
@RestController
@CrossOrigin
public class PermissionRolePermissionController {

@Autowired
 private PermissionRolePermissionService permissionrolepermissionservice;


@GetMapping
("/RolePermission/{id}/Permission/getPermission")
public Permission getPermission(@PathVariable(name="id") String idJL3E){
return permissionrolepermissionservice.getPermission(idJL3E);
}


@PutMapping
("/RolePermission/{id}/Permission/setPermission")
public void setPermission(@PathVariable(name="id") String idJL3E,@RequestBody Permission permission){
permissionrolepermissionservice.setPermission(idJL3E,permission);
}


}