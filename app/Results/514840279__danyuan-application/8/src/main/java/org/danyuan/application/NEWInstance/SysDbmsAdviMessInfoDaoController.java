package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsAdviMessInfoDaoController {

 private SysDbmsAdviMessInfoDao sysdbmsadvimessinfodao;


@GetMapping
("/deleteAllInBatch")
public Object deleteAllInBatch(@RequestParam(name = "Object") Object Object){
  return sysdbmsadvimessinfodao.deleteAllInBatch(Object);
}


}