package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsTabsTypeInfoServiceController {

 private SysDbmsTabsTypeInfoService sysdbmstabstypeinfoservice;


@GetMapping
("/findAllTypeByUser")
public List<SysDbmsTabsTypeInfo> findAllTypeByUser(@RequestParam(name = "username") String username){
  return sysdbmstabstypeinfoservice.findAllTypeByUser(username);
}


@GetMapping
("/findAll")
public List<SysDbmsTabsTypeInfo> findAll(){
  return sysdbmstabstypeinfoservice.findAll();
}


}