package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ParentController {

 private Parent parent;

 private Parent parent;


@PutMapping
("/setLname")
public void setLname(@RequestParam(name = "lname") String lname){
parent.setLname(lname);
}


}