package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResourceTypeServiceController {

 private ResourceTypeService resourcetypeservice;


@PutMapping
("/instantiateType")
public void instantiateType(@RequestParam(name = "id") Long id){
resourcetypeservice.instantiateType(id);
}


@GetMapping
("/findWithPropertiesByID")
public ResourceType findWithPropertiesByID(@RequestParam(name = "ID") Long ID){
  return resourcetypeservice.findWithPropertiesByID(ID);
}


}