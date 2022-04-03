package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ChoosePoliticsDaoController {

 private ChoosePoliticsDao choosepoliticsdao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return choosepoliticsdao.findAll(Object);
}


}