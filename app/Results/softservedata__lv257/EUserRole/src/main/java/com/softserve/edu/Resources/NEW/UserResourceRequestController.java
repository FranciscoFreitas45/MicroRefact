package com.softserve.edu.Resources.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.entity.User;
@RestController
@CrossOrigin
public class UserResourceRequestController {

@Autowired
 private UserResourceRequestService userresourcerequestservice;


@GetMapping
("/ResourceRequest/{id}/User/setResourcesAdmin")
public ResourceRequest setResourcesAdmin(@PathVariable(name="id") Long idSND5,@RequestParam User resourcesAdmin){
return userresourcerequestservice.setResourcesAdmin(idSND5,resourcesAdmin);
}


@GetMapping
("/ResourceRequest/{id}/User/setRegister")
public ResourceRequest setRegister(@PathVariable(name="id") Long idSND5,@RequestParam User register){
return userresourcerequestservice.setRegister(idSND5,register);
}


@GetMapping
("/ResourceRequest/{id}/User/getRegister")
public User getRegister(@PathVariable(name="id") Long idSND5){
return userresourcerequestservice.getRegister(idSND5);
}


@GetMapping
("/ResourceRequest/{id}/User/getResourcesAdmin")
public User getResourcesAdmin(@PathVariable(name="id") Long idSND5){
return userresourcerequestservice.getResourcesAdmin(idSND5);
}


}