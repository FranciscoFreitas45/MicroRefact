package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AccessoriesTypeServiceController {

 private AccessoriesTypeService accessoriestypeservice;


@GetMapping
("/queryAll")
public Object queryAll(@RequestParam(name = "Object") Object Object){
  return accessoriestypeservice.queryAll(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return accessoriestypeservice.count(Object);
}


@GetMapping
("/queryByPager")
public Object queryByPager(@RequestParam(name = "Object") Object Object){
  return accessoriestypeservice.queryByPager(Object);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return accessoriestypeservice.insert(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return accessoriestypeservice.update(Object);
}


@GetMapping
("/countByDisable")
public int countByDisable(){
  return accessoriestypeservice.countByDisable();
}


@GetMapping
("/queryByPagerDisable")
public Object queryByPagerDisable(@RequestParam(name = "Object") Object Object){
  return accessoriestypeservice.queryByPagerDisable(Object);
}


@GetMapping
("/inactive")
public Object inactive(@RequestParam(name = "Object") Object Object){
  return accessoriestypeservice.inactive(Object);
}


@GetMapping
("/active")
public Object active(@RequestParam(name = "Object") Object Object){
  return accessoriestypeservice.active(Object);
}


@GetMapping
("/blurredQuery")
public Object blurredQuery(@RequestParam(name = "Object") Object Object){
  return accessoriestypeservice.blurredQuery(Object);
}


@GetMapping
("/countByBlurred")
public Object countByBlurred(@RequestParam(name = "Object") Object Object){
  return accessoriestypeservice.countByBlurred(Object);
}


@GetMapping
("/queryTypeName")
public List<AccessoriesType> queryTypeName(@RequestParam(name = "companyId") String companyId){
  return accessoriestypeservice.queryTypeName(companyId);
}


@GetMapping
("/queryAccTypeNameOne")
public int queryAccTypeNameOne(@RequestParam(name = "accTypeName") String accTypeName,@RequestParam(name = "accTypeId") String accTypeId){
  return accessoriestypeservice.queryAccTypeNameOne(accTypeName,accTypeId);
}


}