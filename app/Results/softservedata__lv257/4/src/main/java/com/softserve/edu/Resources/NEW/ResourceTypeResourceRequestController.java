package com.softserve.edu.Resources.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.entity.ResourceType;
@RestController
@CrossOrigin
public class ResourceTypeResourceRequestController {

@Autowired
 private ResourceTypeResourceRequestService resourcetyperesourcerequestservice;


@GetMapping
("/ResourceRequest/{id}/ResourceType/getResourceType")
public ResourceType getResourceType(@PathVariable(name="id") Long idO93K){
return resourcetyperesourcerequestservice.getResourceType(idO93K);
}


@GetMapping
("/ResourceRequest/{id}/ResourceType/setResourceType")
public ResourceRequest setResourceType(@PathVariable(name="id") Long idO93K,@RequestParam ResourceType resourceType){
return resourcetyperesourcerequestservice.setResourceType(idO93K,resourceType);
}


}