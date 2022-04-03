package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResourceRequestController {

 private ResourceRequestDAOImpl resourcerequestdaoimpl;


@GetMapping
("/setResourceType/{id}")
public ResourceRequest setResourceType(@PathVariable(name = "id") long id,@RequestParam(name = "resourceType") ResourceType resourceType){
 return resourcerequestdaoimpl.setResourceType(id,resourceType);
}


@GetMapping
("/setStatus/{id}")
public ResourceRequest setStatus(@PathVariable(name = "id") long id,@RequestParam(name = "status") Status status){
 return resourcerequestdaoimpl.setStatus(id,status);
}


}