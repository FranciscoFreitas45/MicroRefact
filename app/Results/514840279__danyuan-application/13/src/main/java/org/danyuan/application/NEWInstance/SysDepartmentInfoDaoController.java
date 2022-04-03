package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDepartmentInfoDaoController {

 private SysDepartmentInfoDao sysdepartmentinfodao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return sysdepartmentinfodao.findAll(Object);
}


}