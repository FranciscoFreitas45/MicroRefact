package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersTourBlackListDaoController {

 private CreepersTourBlackListDao creeperstourblacklistdao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return creeperstourblacklistdao.findAll(Object);
}


}