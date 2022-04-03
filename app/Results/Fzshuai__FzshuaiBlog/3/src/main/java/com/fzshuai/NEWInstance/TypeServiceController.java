package com.fzshuai.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TypeServiceController {

 private TypeService typeservice;


@GetMapping
("/listTypeTop")
public List<Type> listTypeTop(@RequestParam(name = "size") Integer size){
  return typeservice.listTypeTop(size);
}


}