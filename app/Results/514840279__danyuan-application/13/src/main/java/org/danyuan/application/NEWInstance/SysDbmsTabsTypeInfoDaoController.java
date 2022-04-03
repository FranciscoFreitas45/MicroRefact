package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsTabsTypeInfoDaoController {

 private SysDbmsTabsTypeInfoDao sysdbmstabstypeinfodao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return sysdbmstabstypeinfodao.findAll(Object);
}


}