package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsChartDimensionGroupDaoController {

 private SysDbmsChartDimensionGroupDao sysdbmschartdimensiongroupdao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return sysdbmschartdimensiongroupdao.findAll(Object);
}


}