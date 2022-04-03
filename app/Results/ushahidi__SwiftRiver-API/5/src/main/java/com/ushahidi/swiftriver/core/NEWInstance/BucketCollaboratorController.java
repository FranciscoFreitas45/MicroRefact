package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BucketCollaboratorController {

 private JpaBucketDao jpabucketdao;


@GetMapping
("/isActive/{id}")
public boolean isActive(@PathVariable(name = "id") long id){
 return jpabucketdao.isActive(id);
}


@PutMapping
("/setActive/{id}")
public void setActive(@PathVariable(name = "id") long id,@RequestParam(name = "active") boolean active){
 jpabucketdao.setActive(id,active);
}


@PutMapping
("/setReadOnly/{id}")
public void setReadOnly(@PathVariable(name = "id") long id,@RequestParam(name = "readOnly") boolean readOnly){
 jpabucketdao.setReadOnly(id,readOnly);
}


}