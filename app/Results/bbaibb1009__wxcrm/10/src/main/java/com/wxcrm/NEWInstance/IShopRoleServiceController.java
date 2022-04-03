package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IShopRoleServiceController {

 private IShopRoleService ishoproleservice;


@GetMapping
("/queryShopRoleForAdminAdd")
public List<WcShopRole> queryShopRoleForAdminAdd(){
  return ishoproleservice.queryShopRoleForAdminAdd();
}


@GetMapping
("/queryShopRoleForAdminUpd0")
public List<WcShopRole> queryShopRoleForAdminUpd0(@RequestParam(name = "adminId") Integer adminId){
  return ishoproleservice.queryShopRoleForAdminUpd0(adminId);
}


@GetMapping
("/queryShopRoleForAdminUpd1")
public List<WcShopRole> queryShopRoleForAdminUpd1(@RequestParam(name = "adminId") Integer adminId){
  return ishoproleservice.queryShopRoleForAdminUpd1(adminId);
}


}