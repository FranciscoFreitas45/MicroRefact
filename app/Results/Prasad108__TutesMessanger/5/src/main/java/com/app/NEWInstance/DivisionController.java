package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DivisionController {

 private Division division;

 private Division division;


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
division.setName(name);
}


@PutMapping
("/setClasses")
public void setClasses(@RequestParam(name = "classes") Classes classes){
division.setClasses(classes);
}


}