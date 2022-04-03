package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BasicController {

 private BasicRepository basicrepository;


@PutMapping
("/setCreate_time/{id}")
public void setCreate_time(@PathVariable(name = "id") Integer id,@RequestParam(name = "create_time") DateTime create_time){
 basicrepository.setCreate_time(id,create_time);
}


@PutMapping
("/setUpdate_time/{id}")
public void setUpdate_time(@PathVariable(name = "id") Integer id,@RequestParam(name = "update_time") DateTime update_time){
 basicrepository.setUpdate_time(id,update_time);
}


@GetMapping
("/toString/{id}")
public String toString(@PathVariable(name = "id") Integer id){
 return basicrepository.toString(id);
}


@PutMapping
("/setIsDelete/{id}")
public void setIsDelete(@PathVariable(name = "id") Integer id,@RequestParam(name = "isDelete") Integer isDelete){
 basicrepository.setIsDelete(id,isDelete);
}


}