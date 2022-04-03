package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysRolesInfoDaoController {

 private SysRolesInfoDao sysrolesinfodao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return sysrolesinfodao.findAll(Object);
}


}