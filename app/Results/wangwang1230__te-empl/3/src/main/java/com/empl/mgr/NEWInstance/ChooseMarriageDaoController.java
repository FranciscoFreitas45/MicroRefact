package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ChooseMarriageDaoController {

 private ChooseMarriageDao choosemarriagedao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return choosemarriagedao.findAll(Object);
}


}