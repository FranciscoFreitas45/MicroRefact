package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ConstrainedPropertyController {

 private ConstrainedProperty constrainedproperty;


@GetMapping
("/isRequired")
public boolean isRequired(){
  return constrainedproperty.isRequired();
}


@GetMapping
("/hashCode")
public Object hashCode(@RequestParam(name = "Object") Object Object){
  return constrainedproperty.hashCode(Object);
}


@GetMapping
("/equals")
public Object equals(@RequestParam(name = "Object") Object Object){
  return constrainedproperty.equals(Object);
}


@GetMapping
("/getProperty")
public ResourceProperty getProperty(){
  return constrainedproperty.getProperty();
}


}