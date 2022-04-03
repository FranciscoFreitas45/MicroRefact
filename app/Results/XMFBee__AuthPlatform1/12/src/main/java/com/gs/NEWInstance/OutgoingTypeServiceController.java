package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OutgoingTypeServiceController {

 private OutgoingTypeService outgoingtypeservice;


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return outgoingtypeservice.count(Object);
}


@GetMapping
("/queryByPager")
public Object queryByPager(@RequestParam(name = "Object") Object Object){
  return outgoingtypeservice.queryByPager(Object);
}


@GetMapping
("/countByDisable")
public int countByDisable(){
  return outgoingtypeservice.countByDisable();
}


@GetMapping
("/queryByPagerDisable")
public List<OutgoingType> queryByPagerDisable(@RequestParam(name = "pager") Pager pager){
  return outgoingtypeservice.queryByPagerDisable(pager);
}


@GetMapping
("/inactive")
public Object inactive(@RequestParam(name = "Object") Object Object){
  return outgoingtypeservice.inactive(Object);
}


@GetMapping
("/active")
public Object active(@RequestParam(name = "Object") Object Object){
  return outgoingtypeservice.active(Object);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return outgoingtypeservice.insert(Object);
}


@GetMapping
("/queryById")
public OutgoingType queryById(@RequestParam(name = "outTypeName") String outTypeName,@RequestParam(name = "outTypeId") String outTypeId){
  return outgoingtypeservice.queryById(outTypeName,outTypeId);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return outgoingtypeservice.update(Object);
}


@GetMapping
("/queryAll")
public Object queryAll(@RequestParam(name = "Object") Object Object){
  return outgoingtypeservice.queryAll(Object);
}


}