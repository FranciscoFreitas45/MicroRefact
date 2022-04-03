package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsChartDimensionDataDaoController {

 private SysDbmsChartDimensionDataDao sysdbmschartdimensiondatadao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return sysdbmschartdimensiondatadao.findAll(Object);
}


}