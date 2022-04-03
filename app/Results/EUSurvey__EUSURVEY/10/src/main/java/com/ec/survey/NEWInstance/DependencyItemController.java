package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DependencyItemController {

 private DependencyItem dependencyitem;

 private DependencyItem dependencyitem;


@PutMapping
("/setPosition")
public void setPosition(@RequestParam(name = "position") Integer position){
dependencyitem.setPosition(position);
}


}