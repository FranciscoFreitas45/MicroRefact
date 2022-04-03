package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EntityServiceController {

 private EntityService entityservice;


@GetMapping
("/getRowEntity")
public T getRowEntity(@RequestParam(name = "domain") T domain){
  return entityservice.getRowEntity(domain);
}


@GetMapping
("/updateEntity")
public int updateEntity(@RequestParam(name = "domain") T domain){
  return entityservice.updateEntity(domain);
}


@GetMapping
("/insertEntity")
public int insertEntity(@RequestParam(name = "domain") T domain){
  return entityservice.insertEntity(domain);
}


@GetMapping
("/deleteEntity")
public int deleteEntity(@RequestParam(name = "domain") T domain){
  return entityservice.deleteEntity(domain);
}


@GetMapping
("/getCountEntity")
public int getCountEntity(@RequestParam(name = "domain") T domain){
  return entityservice.getCountEntity(domain);
}


@GetMapping
("/getListEntityOrdered")
public List<T> getListEntityOrdered(@RequestParam(name = "domain") T domain,@RequestParam(name = "orderQuery") String orderQuery){
  return entityservice.getListEntityOrdered(domain,orderQuery);
}


@GetMapping
("/getListEntity")
public List<T> getListEntity(@RequestParam(name = "domain") T domain){
  return entityservice.getListEntity(domain);
}


}