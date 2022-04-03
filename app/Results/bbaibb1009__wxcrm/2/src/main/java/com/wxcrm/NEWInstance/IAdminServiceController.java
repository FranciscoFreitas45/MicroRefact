package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IAdminServiceController {

 private IAdminService iadminservice;


@GetMapping
("/chkUsernameUnique")
public boolean chkUsernameUnique(@RequestParam(name = "username") String username){
  return iadminservice.chkUsernameUnique(username);
}


@GetMapping
("/adminLogin")
public WcAdmin adminLogin(@RequestParam(name = "admin") WcAdmin admin){
  return iadminservice.adminLogin(admin);
}


@GetMapping
("/queryAdminMenus")
public List<Map<String,Object>> queryAdminMenus(@RequestParam(name = "adminId") Integer adminId,@RequestParam(name = "menuLevel") String menuLevel){
  return iadminservice.queryAdminMenus(adminId,menuLevel);
}


@PutMapping
("/updLoginTime")
public void updLoginTime(@RequestParam(name = "adminId") Integer adminId){
iadminservice.updLoginTime(adminId);
}


@GetMapping
("/queryAdminNameToCache")
public List<Map<String,Object>> queryAdminNameToCache(){
  return iadminservice.queryAdminNameToCache();
}


}