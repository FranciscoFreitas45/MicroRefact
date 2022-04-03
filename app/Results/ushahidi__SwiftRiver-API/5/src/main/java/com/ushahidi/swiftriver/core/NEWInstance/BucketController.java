package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BucketController {

 private JpaBucketDao jpabucketdao;


@GetMapping
("/isPublished/{id}")
public boolean isPublished(@PathVariable(name = "id") long id){
 return jpabucketdao.isPublished(id);
}


@PutMapping
("/setName/{id}")
public void setName(@PathVariable(name = "id") long id,@RequestParam(name = "name") String name){
 jpabucketdao.setName(id,name);
}


@PutMapping
("/setPublished/{id}")
public void setPublished(@PathVariable(name = "id") long id,@RequestParam(name = "published") boolean published){
 jpabucketdao.setPublished(id,published);
}


@PutMapping
("/setAccount/{id}")
public void setAccount(@PathVariable(name = "id") long id,@RequestParam(name = "account") Account account){
 jpabucketdao.setAccount(id,account);
}


@PutMapping
("/setDateAdded/{id}")
public void setDateAdded(@PathVariable(name = "id") long id,@RequestParam(name = "dateAdded") Date dateAdded){
 jpabucketdao.setDateAdded(id,dateAdded);
}


@PutMapping
("/setId/{id}")
public void setId(@PathVariable(name = "id") long id,@RequestParam(name = "id") long id){
 jpabucketdao.setId(id,id);
}


}