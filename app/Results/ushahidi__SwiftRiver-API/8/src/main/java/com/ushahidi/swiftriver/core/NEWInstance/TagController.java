package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TagController {

 private JpaTagDao jpatagdao;


@PutMapping
("/setTag/{id}")
public void setTag(@PathVariable(name = "id") long id,@RequestParam(name = "tag") String tag){
 jpatagdao.setTag(id,tag);
}


@PutMapping
("/setType/{id}")
public void setType(@PathVariable(name = "id") long id,@RequestParam(name = "type") String type){
 jpatagdao.setType(id,type);
}


@PutMapping
("/setId/{id}")
public void setId(@PathVariable(name = "id") long id,@RequestParam(name = "id") long id){
 jpatagdao.setId(id,id);
}


}