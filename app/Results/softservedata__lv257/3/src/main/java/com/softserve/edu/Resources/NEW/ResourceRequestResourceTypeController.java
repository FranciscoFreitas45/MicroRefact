package com.softserve.edu.Resources.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.entity.ResourceRequest;
@RestController
@CrossOrigin
public class ResourceRequestResourceTypeController {

@Autowired
 private ResourceRequestResourceTypeService resourcerequestresourcetypeservice;


@GetMapping
("/ResourceType/{id}/ResourceRequest/setRequest")
public ResourceType setRequest(@PathVariable(name="id") long idW6RM,@RequestParam ResourceRequest request){
return resourcerequestresourcetypeservice.setRequest(idW6RM,request);
}


@GetMapping
("/ResourceType/{id}/ResourceRequest/getRequest")
public ResourceRequest getRequest(@PathVariable(name="id") long idW6RM){
return resourcerequestresourcetypeservice.getRequest(idW6RM);
}


}