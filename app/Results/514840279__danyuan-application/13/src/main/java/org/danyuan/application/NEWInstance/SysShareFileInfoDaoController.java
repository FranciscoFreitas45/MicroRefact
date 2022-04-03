package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysShareFileInfoDaoController {

 private SysShareFileInfoDao syssharefileinfodao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return syssharefileinfodao.findAll(Object);
}


}