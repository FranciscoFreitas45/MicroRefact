package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsTabsMergeInfoServiceController {

 private SysDbmsTabsMergeInfoService sysdbmstabsmergeinfoservice;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return sysdbmstabsmergeinfoservice.findOne(Object);
}


@GetMapping
("/page1")
public List<SysDbmsTabsColsInfo> page1(@RequestParam(name = "vo") Pagination<SysDbmsTabsMergeInfo> vo){
  return sysdbmstabsmergeinfoservice.page1(vo);
}


@GetMapping
("/page2")
public List<SysDbmsTabsColsInfo> page2(@RequestParam(name = "vo") Pagination<SysDbmsTabsMergeInfo> vo){
  return sysdbmstabsmergeinfoservice.page2(vo);
}


@GetMapping
("/merge")
public String merge(@RequestParam(name = "vo") Pagination<SysDbmsTabsMergeInfo> vo){
  return sysdbmstabsmergeinfoservice.merge(vo);
}


@GetMapping
("/loadSql")
public String loadSql(@RequestParam(name = "vo") SysDbmsTabsMergeInfo vo){
  return sysdbmstabsmergeinfoservice.loadSql(vo);
}


}