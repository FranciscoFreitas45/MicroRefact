package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResourceCategoryServiceController {

 private ResourceCategoryService resourcecategoryservice;


@GetMapping
("/findCategoryById")
public Optional<ResourceCategory> findCategoryById(@RequestParam(name = "id") Long id){
  return resourcecategoryservice.findCategoryById(id);
}


}