package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IdentityController {

 private JpaIdentityDao jpaidentitydao;


@PutMapping
("/setName/{id}")
public void setName(@PathVariable(name = "id") long id,@RequestParam(name = "name") String name){
 jpaidentitydao.setName(id,name);
}


@PutMapping
("/setAvatar/{id}")
public void setAvatar(@PathVariable(name = "id") long id,@RequestParam(name = "avatar") String avatar){
 jpaidentitydao.setAvatar(id,avatar);
}


}