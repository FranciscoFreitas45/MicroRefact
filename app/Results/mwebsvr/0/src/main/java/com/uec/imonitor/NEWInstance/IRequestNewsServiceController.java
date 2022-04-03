package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IRequestNewsServiceController {

 private IRequestNewsService irequestnewsservice;


@GetMapping
("/saveRequestNews")
public RequestNewsEntity saveRequestNews(@RequestParam(name = "entity") RequestNewsEntity entity){
  return irequestnewsservice.saveRequestNews(entity);
}


@GetMapping
("/listDetailByIds")
public List<RequestNewsDetail> listDetailByIds(@RequestParam(name = "idList") List<Integer> idList){
  return irequestnewsservice.listDetailByIds(idList);
}


@GetMapping
("/add")
public RequestNewsEntity add(@RequestParam(name = "entity") RequestNewsEntity entity){
  return irequestnewsservice.add(entity);
}


}