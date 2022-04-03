package com.softserve.edu.Resources.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.entity.ResourceType;
@RestController
@CrossOrigin
public class ResourceTypeResourceOwningController {

@Autowired
 private ResourceTypeResourceOwningService resourcetyperesourceowningservice;


@GetMapping
("/ResourceOwning/{id}/ResourceType/getResourceType")
public ResourceType getResourceType(@PathVariable(name="id") Long id2JVR){
return resourcetyperesourceowningservice.getResourceType(id2JVR);
}


@PutMapping
("/ResourceOwning/{id}/ResourceType/setResourceType")
public void setResourceType(@PathVariable(name="id") Long id2JVR,@RequestBody ResourceType resourceType){
resourcetyperesourceowningservice.setResourceType(id2JVR,resourceType);
}


}