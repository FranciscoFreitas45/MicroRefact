package com.fzshuai.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.po.Type;
@RestController
@CrossOrigin
public class TypeBlogController {

@Autowired
 private TypeBlogService typeblogservice;


@PutMapping
("/Blog/{id}/Type/setType")
public void setType(@PathVariable(name="id") Long id,@RequestBody Type type){
typeblogservice.setType(id,type);
}


@GetMapping
("/Blog/{id}/Type/getType")
public Type getType(@PathVariable(name="id") Long id){
return typeblogservice.getType(id);
}


}