package com.softserve.edu.Resources.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.entity.ResourceCategory;
@RestController
@CrossOrigin
public class ResourceCategoryResourceTypeController {

@Autowired
 private ResourceCategoryResourceTypeService resourcecategoryresourcetypeservice;


@GetMapping
("/ResourceType/{id}/ResourceCategory/getCategory")
public ResourceCategory getCategory(@PathVariable(name="id") Long idWR9B){
return resourcecategoryresourcetypeservice.getCategory(idWR9B);
}


@GetMapping
("/ResourceType/{id}/ResourceCategory/setCategory")
public ResourceType setCategory(@PathVariable(name="id") Long idWR9B,@RequestParam ResourceCategory category){
return resourcecategoryresourcetypeservice.setCategory(idWR9B,category);
}


}