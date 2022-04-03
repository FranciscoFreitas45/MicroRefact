package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IncomingTypeServiceController {

 private IncomingTypeService incomingtypeservice;


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return incomingtypeservice.count(Object);
}


@GetMapping
("/queryByPager")
public Object queryByPager(@RequestParam(name = "Object") Object Object){
  return incomingtypeservice.queryByPager(Object);
}


@GetMapping
("/countByDisable")
public int countByDisable(){
  return incomingtypeservice.countByDisable();
}


@GetMapping
("/queryByPagerDisable")
public List<IncomingType> queryByPagerDisable(@RequestParam(name = "pager") Pager pager){
  return incomingtypeservice.queryByPagerDisable(pager);
}


@GetMapping
("/inactive")
public Object inactive(@RequestParam(name = "Object") Object Object){
  return incomingtypeservice.inactive(Object);
}


@GetMapping
("/active")
public Object active(@RequestParam(name = "Object") Object Object){
  return incomingtypeservice.active(Object);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return incomingtypeservice.insert(Object);
}


@GetMapping
("/queryById")
public IncomingType queryById(@RequestParam(name = "inTypeName") String inTypeName,@RequestParam(name = "inTypeId") String inTypeId){
  return incomingtypeservice.queryById(inTypeName,inTypeId);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return incomingtypeservice.update(Object);
}


@GetMapping
("/queryAll")
public Object queryAll(@RequestParam(name = "Object") Object Object){
  return incomingtypeservice.queryAll(Object);
}


}