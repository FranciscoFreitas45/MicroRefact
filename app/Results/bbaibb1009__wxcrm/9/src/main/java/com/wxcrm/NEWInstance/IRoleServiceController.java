package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IRoleServiceController {

 private IRoleService iroleservice;


@GetMapping
("/queryRoleForAdminAdd")
public List<WcRole> queryRoleForAdminAdd(){
  return iroleservice.queryRoleForAdminAdd();
}


@GetMapping
("/queryRoleForAdminUpd0")
public List<WcRole> queryRoleForAdminUpd0(@RequestParam(name = "adminId") Integer adminId){
  return iroleservice.queryRoleForAdminUpd0(adminId);
}


@GetMapping
("/queryRoleForAdminUpd1")
public List<WcRole> queryRoleForAdminUpd1(@RequestParam(name = "adminId") Integer adminId){
  return iroleservice.queryRoleForAdminUpd1(adminId);
}


@GetMapping
("/queryRoleMenusById")
public List<String> queryRoleMenusById(@RequestParam(name = "roleId") Integer roleId){
  return iroleservice.queryRoleMenusById(roleId);
}


}