package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BucketDropController {

 private JpaBucketDropDao jpabucketdropdao;


@PutMapping
("/setBucket/{id}")
public void setBucket(@PathVariable(name = "id") long id,@RequestParam(name = "bucket") Bucket bucket){
 jpabucketdropdao.setBucket(id,bucket);
}


@PutMapping
("/setDrop/{id}")
public void setDrop(@PathVariable(name = "id") long id,@RequestParam(name = "drop") Drop drop){
 jpabucketdropdao.setDrop(id,drop);
}


@PutMapping
("/setDateAdded/{id}")
public void setDateAdded(@PathVariable(name = "id") long id,@RequestParam(name = "dateAdded") Date dateAdded){
 jpabucketdropdao.setDateAdded(id,dateAdded);
}


@PutMapping
("/setVeracity/{id}")
public void setVeracity(@PathVariable(name = "id") long id,@RequestParam(name = "veracity") long veracity){
 jpabucketdropdao.setVeracity(id,veracity);
}


}