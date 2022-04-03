package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClassesController {

 private Classes classes;

 private Classes classes;


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
classes.setName(name);
}


}