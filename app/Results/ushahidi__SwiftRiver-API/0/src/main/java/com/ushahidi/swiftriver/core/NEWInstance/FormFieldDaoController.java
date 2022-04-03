package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FormFieldDaoController {

 private FormFieldDao formfielddao;


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return formfielddao.create(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return formfielddao.update(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return formfielddao.delete(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return formfielddao.findById(Object);
}


}