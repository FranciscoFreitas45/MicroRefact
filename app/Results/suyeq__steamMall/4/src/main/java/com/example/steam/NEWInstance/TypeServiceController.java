package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TypeServiceController {

 private TypeService typeservice;


@GetMapping
("/findAllType")
public List<String> findAllType(){
  return typeservice.findAllType();
}


@GetMapping
("/deleteTypeById")
public int deleteTypeById(@RequestParam(name = "typeId") long typeId){
  return typeservice.deleteTypeById(typeId);
}


@GetMapping
("/addType")
public int addType(@RequestParam(name = "typeName") String typeName){
  return typeservice.addType(typeName);
}


@GetMapping
("/findAllTypes")
public List<Type> findAllTypes(){
  return typeservice.findAllTypes();
}


}