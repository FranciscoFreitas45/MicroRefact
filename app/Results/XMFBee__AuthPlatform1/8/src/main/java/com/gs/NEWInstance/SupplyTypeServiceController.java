package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SupplyTypeServiceController {

 private SupplyTypeService supplytypeservice;


@GetMapping
("/queryAll")
public Object queryAll(@RequestParam(name = "Object") Object Object){
  return supplytypeservice.queryAll(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return supplytypeservice.count(Object);
}


@GetMapping
("/queryByPager")
public Object queryByPager(@RequestParam(name = "Object") Object Object){
  return supplytypeservice.queryByPager(Object);
}


@GetMapping
("/queryNameByOne")
public int queryNameByOne(@RequestParam(name = "supplyTypeName") String supplyTypeName,@RequestParam(name = "supplyTypeId") String supplyTypeId){
  return supplytypeservice.queryNameByOne(supplyTypeName,supplyTypeId);
}


@GetMapping
("/countByDisable")
public int countByDisable(){
  return supplytypeservice.countByDisable();
}


@GetMapping
("/queryByPagerDisable")
public List<SupplyType> queryByPagerDisable(@RequestParam(name = "pager") Pager pager){
  return supplytypeservice.queryByPagerDisable(pager);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return supplytypeservice.insert(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return supplytypeservice.update(Object);
}


@GetMapping
("/inactive")
public Object inactive(@RequestParam(name = "Object") Object Object){
  return supplytypeservice.inactive(Object);
}


@GetMapping
("/active")
public Object active(@RequestParam(name = "Object") Object Object){
  return supplytypeservice.active(Object);
}


}