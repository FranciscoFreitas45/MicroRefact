package com.gbcom.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysUserManagerController {

 private SysUserManager sysusermanager;


@GetMapping
("/hasPrivilege")
public Boolean hasPrivilege(@RequestParam(name = "userId") Long userId,@RequestParam(name = "privilegeCode") String privilegeCode){
  return sysusermanager.hasPrivilege(userId,privilegeCode);
}


@GetMapping
("/getDisplayName")
public String getDisplayName(@RequestParam(name = "loginName") String loginName){
  return sysusermanager.getDisplayName(loginName);
}


@GetMapping
("/getUserPrivilegeCodesCache")
public Set<String> getUserPrivilegeCodesCache(@RequestParam(name = "userId") Long userId){
  return sysusermanager.getUserPrivilegeCodesCache(userId);
}


@GetMapping
("/getSysUser")
public SysUser getSysUser(){
  return sysusermanager.getSysUser();
}


@GetMapping
("/getSysUserRoles")
public Object getSysUserRoles(@RequestParam(name = "Object") Object Object){
  return sysusermanager.getSysUserRoles(Object);
}


@GetMapping
("/getRoleList")
public List<Map<String,Object>> getRoleList(@RequestParam(name = "bean") SysUser bean){
  return sysusermanager.getRoleList(bean);
}


@PutMapping
("/saveRoles")
public void saveRoles(@RequestParam(name = "bean") SysUser bean,@RequestParam(name = "roleIds") String[] roleIds){
sysusermanager.saveRoles(bean,roleIds);
}


@PutMapping
("/delete")
public void delete(@RequestParam(name = "id") Long id){
sysusermanager.delete(id);
}


@GetMapping
("/getArea")
public Object getArea(@RequestParam(name = "Object") Object Object){
  return sysusermanager.getArea(Object);
}


@GetMapping
("/getId")
public Object getId(@RequestParam(name = "Object") Object Object){
  return sysusermanager.getId(Object);
}


}