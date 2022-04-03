package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DivisionServiceController {

 private DivisionService divisionservice;


@GetMapping
("/getallOfParticularClass")
public List<Division> getallOfParticularClass(@RequestParam(name = "classes") Classes classes){
  return divisionservice.getallOfParticularClass(classes);
}


@PutMapping
("/create")
public void create(@RequestParam(name = "div") Division div){
divisionservice.create(div);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "classes") Division classes){
divisionservice.update(classes);
}


@PutMapping
("/delet")
public void delet(@RequestParam(name = "id") int id){
divisionservice.delet(id);
}


@GetMapping
("/find")
public Division find(@RequestParam(name = "id") int id){
  return divisionservice.find(id);
}


}