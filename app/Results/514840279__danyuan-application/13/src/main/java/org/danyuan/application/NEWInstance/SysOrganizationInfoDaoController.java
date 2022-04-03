package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysOrganizationInfoDaoController {

 private SysOrganizationInfoDao sysorganizationinfodao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return sysorganizationinfodao.findAll(Object);
}


}