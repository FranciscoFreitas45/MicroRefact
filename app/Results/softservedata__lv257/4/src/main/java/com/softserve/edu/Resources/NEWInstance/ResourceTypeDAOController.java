package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResourceTypeDAOController {

 private ResourceTypeDAO resourcetypedao;


@GetMapping
("/findWithPropertiesByID")
public ResourceType findWithPropertiesByID(@RequestParam(name = "resourceTypeID") Long resourceTypeID){
  return resourcetypedao.findWithPropertiesByID(resourceTypeID);
}


@GetMapping
("/findByTypeName")
public Optional<ResourceType> findByTypeName(@RequestParam(name = "typeName") String typeName){
  return resourcetypedao.findByTypeName(typeName);
}


@GetMapping
("/findById")
public Optional<ResourceType> findById(@RequestParam(name = "id") Long id,@RequestParam(name = "doFetch") boolean doFetch){
  return resourcetypedao.findById(id,doFetch);
}


}