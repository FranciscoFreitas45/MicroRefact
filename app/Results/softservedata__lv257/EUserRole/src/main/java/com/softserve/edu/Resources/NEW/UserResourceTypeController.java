package com.softserve.edu.Resources.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.entity.User;
@RestController
@CrossOrigin
public class UserResourceTypeController {

@Autowired
 private UserResourceTypeService userresourcetypeservice;


@GetMapping
("/ResourceType/{id}/User/setAssigner")
public ResourceType setAssigner(@PathVariable(name="id") Long id93GK,@RequestParam User assigner){
return userresourcetypeservice.setAssigner(id93GK,assigner);
}


@GetMapping
("/ResourceType/{id}/User/getAssigner")
public User getAssigner(@PathVariable(name="id") Long id93GK){
return userresourcetypeservice.getAssigner(id93GK);
}


}