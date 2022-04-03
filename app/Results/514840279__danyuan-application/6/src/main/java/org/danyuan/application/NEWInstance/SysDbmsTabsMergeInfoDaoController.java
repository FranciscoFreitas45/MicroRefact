package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsTabsMergeInfoDaoController {

 private SysDbmsTabsMergeInfoDao sysdbmstabsmergeinfodao;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return sysdbmstabsmergeinfodao.save(Object);
}


}