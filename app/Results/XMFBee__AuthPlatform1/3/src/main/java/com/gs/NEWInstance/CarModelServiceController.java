package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CarModelServiceController {

 private CarModelService carmodelservice;


@GetMapping
("/queryAll")
public Object queryAll(@RequestParam(name = "Object") Object Object){
  return carmodelservice.queryAll(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return carmodelservice.count(Object);
}


@GetMapping
("/queryByPager")
public Object queryByPager(@RequestParam(name = "Object") Object Object){
  return carmodelservice.queryByPager(Object);
}


@GetMapping
("/queryByBrandId")
public List<CarModel> queryByBrandId(@RequestParam(name = "id") String id){
  return carmodelservice.queryByBrandId(id);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return carmodelservice.insert(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return carmodelservice.update(Object);
}


@GetMapping
("/countByDisable")
public Object countByDisable(@RequestParam(name = "Object") Object Object){
  return carmodelservice.countByDisable(Object);
}


@GetMapping
("/queryByPagerDisable")
public Object queryByPagerDisable(@RequestParam(name = "Object") Object Object){
  return carmodelservice.queryByPagerDisable(Object);
}


@GetMapping
("/querymodelName")
public int querymodelName(@RequestParam(name = "modelName") String modelName,@RequestParam(name = "modelId") String modelId){
  return carmodelservice.querymodelName(modelName,modelId);
}


@GetMapping
("/inactive")
public Object inactive(@RequestParam(name = "Object") Object Object){
  return carmodelservice.inactive(Object);
}


@GetMapping
("/active")
public Object active(@RequestParam(name = "Object") Object Object){
  return carmodelservice.active(Object);
}


@GetMapping
("/blurredQuery")
public Object blurredQuery(@RequestParam(name = "Object") Object Object){
  return carmodelservice.blurredQuery(Object);
}


@GetMapping
("/countByBlurred")
public Object countByBlurred(@RequestParam(name = "Object") Object Object){
  return carmodelservice.countByBlurred(Object);
}


}