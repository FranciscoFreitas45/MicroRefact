package com.ipe.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResourceController {

 private Resource resource;

 private Resource resource;


@PutMapping
("/setParent")
public void setParent(@RequestParam(name = "parent") Resource parent){
resource.setParent(parent);
}


@PutMapping
("/setSno")
public void setSno(@RequestParam(name = "sno") Integer sno){
resource.setSno(sno);
}


@PutMapping
("/setCreatedDate")
public void setCreatedDate(@RequestParam(name = "createdDate") Timestamp createdDate){
resource.setCreatedDate(createdDate);
}


}