package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ChooseEducationDaoController {

 private ChooseEducationDao chooseeducationdao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return chooseeducationdao.findAll(Object);
}


}