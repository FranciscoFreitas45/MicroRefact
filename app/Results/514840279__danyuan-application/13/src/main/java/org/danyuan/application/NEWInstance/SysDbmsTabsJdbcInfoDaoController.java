package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsTabsJdbcInfoDaoController {

 private SysDbmsTabsJdbcInfoDao sysdbmstabsjdbcinfodao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return sysdbmstabsjdbcinfodao.findAll(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return sysdbmstabsjdbcinfodao.findById(Object);
}


}