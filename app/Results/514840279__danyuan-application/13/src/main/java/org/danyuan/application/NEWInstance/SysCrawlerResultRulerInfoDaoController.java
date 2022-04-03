package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysCrawlerResultRulerInfoDaoController {

 private SysCrawlerResultRulerInfoDao syscrawlerresultrulerinfodao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return syscrawlerresultrulerinfodao.findAll(Object);
}


@GetMapping
("/deleteAll")
public Object deleteAll(@RequestParam(name = "Object") Object Object){
  return syscrawlerresultrulerinfodao.deleteAll(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return syscrawlerresultrulerinfodao.save(Object);
}


}