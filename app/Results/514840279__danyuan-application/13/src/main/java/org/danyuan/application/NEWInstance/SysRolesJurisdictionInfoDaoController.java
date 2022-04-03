package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysRolesJurisdictionInfoDaoController {

 private SysRolesJurisdictionInfoDao sysrolesjurisdictioninfodao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return sysrolesjurisdictioninfodao.findAll(Object);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return sysrolesjurisdictioninfodao.findOne(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return sysrolesjurisdictioninfodao.save(Object);
}


}