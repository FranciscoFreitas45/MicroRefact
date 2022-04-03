package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AccessoriesServiceController {

 private AccessoriesService accessoriesservice;


@GetMapping
("/reduceCount")
public int reduceCount(@RequestParam(name = "accCount") int accCount,@RequestParam(name = "accId") String accId){
  return accessoriesservice.reduceCount(accCount,accId);
}


@GetMapping
("/queryAll")
public Object queryAll(@RequestParam(name = "Object") Object Object){
  return accessoriesservice.queryAll(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return accessoriesservice.count(Object);
}


@GetMapping
("/queryByIdPager")
public List<Accessories> queryByIdPager(@RequestParam(name = "id") String id,@RequestParam(name = "pager") Pager pager){
  return accessoriesservice.queryByIdPager(id,pager);
}


@GetMapping
("/queryByPager")
public Object queryByPager(@RequestParam(name = "Object") Object Object){
  return accessoriesservice.queryByPager(Object);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return accessoriesservice.insert(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return accessoriesservice.update(Object);
}


@GetMapping
("/countByDisable")
public Object countByDisable(@RequestParam(name = "Object") Object Object){
  return accessoriesservice.countByDisable(Object);
}


@GetMapping
("/queryByPagerDisable")
public Object queryByPagerDisable(@RequestParam(name = "Object") Object Object){
  return accessoriesservice.queryByPagerDisable(Object);
}


@GetMapping
("/inactive")
public Object inactive(@RequestParam(name = "Object") Object Object){
  return accessoriesservice.inactive(Object);
}


@GetMapping
("/active")
public Object active(@RequestParam(name = "Object") Object Object){
  return accessoriesservice.active(Object);
}


@GetMapping
("/blurredQuery")
public Object blurredQuery(@RequestParam(name = "Object") Object Object){
  return accessoriesservice.blurredQuery(Object);
}


@GetMapping
("/countByBlurred")
public Object countByBlurred(@RequestParam(name = "Object") Object Object){
  return accessoriesservice.countByBlurred(Object);
}


@GetMapping
("/queryById")
public Object queryById(@RequestParam(name = "Object") Object Object){
  return accessoriesservice.queryById(Object);
}


@GetMapping
("/queryByCondition")
public List<Accessories> queryByCondition(@RequestParam(name = "start") String start,@RequestParam(name = "end") String end,@RequestParam(name = "companyId") String companyId,@RequestParam(name = "accTypeId") String accTypeId,@RequestParam(name = "type") String type){
  return accessoriesservice.queryByCondition(start,end,companyId,accTypeId,type);
}


}