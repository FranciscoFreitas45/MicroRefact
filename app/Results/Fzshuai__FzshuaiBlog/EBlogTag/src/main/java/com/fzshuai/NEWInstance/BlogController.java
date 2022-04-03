package com.fzshuai.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BlogController {

 private BlogRepository blogrepository;


@PutMapping
("/setCreateTime/{id}")
public void setCreateTime(@PathVariable(name = "id") Long id,@RequestParam(name = "createTime") Date createTime){
 blogrepository.setCreateTime(id,createTime);
}


@PutMapping
("/setViews/{id}")
public void setViews(@PathVariable(name = "id") Long id,@RequestParam(name = "views") Integer views){
 blogrepository.setViews(id,views);
}


@PutMapping
("/setUser/{id}")
public void setUser(@PathVariable(name = "id") Long id,@RequestParam(name = "user") User user){
 blogrepository.setUser(id,user);
}


@PutMapping
("/setType/{id}")
public void setType(@PathVariable(name = "id") Long id,@RequestParam(name = "type") Type type){
 blogrepository.setType(id,type);
}


}