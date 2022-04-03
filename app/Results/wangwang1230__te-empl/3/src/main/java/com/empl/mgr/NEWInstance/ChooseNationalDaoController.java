package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ChooseNationalDaoController {

 private ChooseNationalDao choosenationaldao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return choosenationaldao.findAll(Object);
}


}