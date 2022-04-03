package com.softserve.edu.Resources.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.entity.ResourceType;
@RestController
@CrossOrigin
public class ResourceTypeResourceCategoryController {

@Autowired
 private ResourceTypeResourceCategoryService resourcetyperesourcecategoryservice;


@GetMapping
("/ResourceCategory/{id}/ResourceType/setResourceTypes")
public ResourceCategory setResourceTypes(@PathVariable(name="id") Long id,@RequestParam Set<ResourceType> resourceTypes){
return resourcetyperesourcecategoryservice.setResourceTypes(id,resourceTypes);
}


@GetMapping
("/ResourceCategory/{id}/ResourceType/getResourceTypes")
public Set<ResourceType> getResourceTypes(@PathVariable(name="id") Long id){
return resourcetyperesourcecategoryservice.getResourceTypes(id);
}


}