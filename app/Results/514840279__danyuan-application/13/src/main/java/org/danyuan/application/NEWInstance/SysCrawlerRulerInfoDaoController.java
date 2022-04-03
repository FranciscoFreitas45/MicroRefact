package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysCrawlerRulerInfoDaoController {

 private SysCrawlerRulerInfoDao syscrawlerrulerinfodao;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return syscrawlerrulerinfodao.save(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return syscrawlerrulerinfodao.findAll(Object);
}


}