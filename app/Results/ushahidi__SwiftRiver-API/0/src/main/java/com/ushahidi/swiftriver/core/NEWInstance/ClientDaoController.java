package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClientDaoController {

 private ClientDao clientdao;


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return clientdao.create(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return clientdao.findById(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return clientdao.delete(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return clientdao.update(Object);
}


}