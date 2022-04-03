package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FormDaoController {

 private FormDao formdao;


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return formdao.create(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return formdao.update(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return formdao.delete(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return formdao.findById(Object);
}


}