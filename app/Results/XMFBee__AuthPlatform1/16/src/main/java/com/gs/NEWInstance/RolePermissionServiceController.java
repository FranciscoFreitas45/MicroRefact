package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RolePermissionServiceController {

 private RolePermissionService rolepermissionservice;


@GetMapping
("/queryPermissions")
public List<Permission> queryPermissions(@RequestParam(name = "roleId") String roleId,@RequestParam(name = "roleStatus") String roleStatus){
  return rolepermissionservice.queryPermissions(roleId,roleStatus);
}


@GetMapping
("/insertList")
public int insertList(@RequestParam(name = "roleId") String roleId,@RequestParam(name = "permissionIds") List<String> permissionIds){
  return rolepermissionservice.insertList(roleId,permissionIds);
}


@GetMapping
("/removePermission")
public int removePermission(@RequestParam(name = "roleId") String roleId,@RequestParam(name = "permissionId") String permissionId){
  return rolepermissionservice.removePermission(roleId,permissionId);
}


@GetMapping
("/addPermission")
public int addPermission(@RequestParam(name = "roleId") String roleId,@RequestParam(name = "permissionId") String permissionId){
  return rolepermissionservice.addPermission(roleId,permissionId);
}


}