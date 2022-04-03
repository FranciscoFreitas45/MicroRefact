package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResourceServiceController {

 private ResourceService resourceservice;


@GetMapping
("/findResourcesByResourceType")
public List<GenericResource> findResourcesByResourceType(@RequestParam(name = "genericResourceDTO") GenericResourceDTO genericResourceDTO){
  return resourceservice.findResourcesByResourceType(genericResourceDTO);
}


@GetMapping
("/findResourcesCountGroupedByResourceTypeForOwner")
public List<GroupedResourceCount> findResourcesCountGroupedByResourceTypeForOwner(@RequestParam(name = "ownerId") String ownerId){
  return resourceservice.findResourcesCountGroupedByResourceTypeForOwner(ownerId);
}


@GetMapping
("/findResourcesByOwnerAndType")
public List<GenericResource> findResourcesByOwnerAndType(@RequestParam(name = "ownerId") long ownerId,@RequestParam(name = "resourceTypeName") String resourceTypeName){
  return resourceservice.findResourcesByOwnerAndType(ownerId,resourceTypeName);
}


}