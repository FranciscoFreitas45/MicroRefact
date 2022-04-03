package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ActivityDaoController {

 private ActivityDao activitydao;


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return activitydao.create(Object);
}


@GetMapping
("/find")
public List<Activity> find(@RequestParam(name = "accountId") long accountId,@RequestParam(name = "count") Integer count,@RequestParam(name = "lastId") Long lastId,@RequestParam(name = "newer") Boolean newer,@RequestParam(name = "followers") Boolean followers){
  return activitydao.find(accountId,count,lastId,newer,followers);
}


}