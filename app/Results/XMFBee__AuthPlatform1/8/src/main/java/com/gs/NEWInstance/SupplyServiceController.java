package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SupplyServiceController {

 private SupplyService supplyservice;


@GetMapping
("/queryAll")
public Object queryAll(@RequestParam(name = "Object") Object Object){
  return supplyservice.queryAll(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return supplyservice.count(Object);
}


@GetMapping
("/queryByPager")
public Object queryByPager(@RequestParam(name = "Object") Object Object){
  return supplyservice.queryByPager(Object);
}


@GetMapping
("/countByDisable")
public Object countByDisable(@RequestParam(name = "Object") Object Object){
  return supplyservice.countByDisable(Object);
}


@GetMapping
("/queryByPagerDisable")
public Object queryByPagerDisable(@RequestParam(name = "Object") Object Object){
  return supplyservice.queryByPagerDisable(Object);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return supplyservice.insert(Object);
}


@GetMapping
("/queryNameByOne")
public int queryNameByOne(@RequestParam(name = "supplyName") String supplyName,@RequestParam(name = "supplyId") String supplyId){
  return supplyservice.queryNameByOne(supplyName,supplyId);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return supplyservice.update(Object);
}


@GetMapping
("/inactive")
public Object inactive(@RequestParam(name = "Object") Object Object){
  return supplyservice.inactive(Object);
}


@GetMapping
("/active")
public Object active(@RequestParam(name = "Object") Object Object){
  return supplyservice.active(Object);
}


@GetMapping
("/blurredQuery")
public Object blurredQuery(@RequestParam(name = "Object") Object Object){
  return supplyservice.blurredQuery(Object);
}


@GetMapping
("/countByBlurred")
public Object countByBlurred(@RequestParam(name = "Object") Object Object){
  return supplyservice.countByBlurred(Object);
}


}