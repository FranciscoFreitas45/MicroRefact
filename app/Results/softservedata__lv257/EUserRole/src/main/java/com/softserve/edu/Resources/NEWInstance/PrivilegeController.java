package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PrivilegeController {

 private PrivilegeDAOImpl privilegedaoimpl;


@PutMapping
("/setName/{id}")
public void setName(@PathVariable(name = "id") Long id,@RequestParam(name = "name") String name){
 privilegedaoimpl.setName(id,name);
}


@PutMapping
("/setDescription/{id}")
public void setDescription(@PathVariable(name = "id") Long id,@RequestParam(name = "description") String description){
 privilegedaoimpl.setDescription(id,description);
}


@PutMapping
("/setPrivilegeType/{id}")
public void setPrivilegeType(@PathVariable(name = "id") Long id,@RequestParam(name = "privilegeType") PrivilegeType privilegeType){
 privilegedaoimpl.setPrivilegeType(id,privilegeType);
}


}