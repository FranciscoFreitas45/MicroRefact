package com.fzshuai.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.po.Blog;
@RestController
@CrossOrigin
public class BlogTypeController {

@Autowired
 private BlogTypeService blogtypeservice;


@PutMapping
("/Type/{id}/Blog/setBlogs")
public void setBlogs(@PathVariable(name="id") Long id,@RequestBody List<Blog> blogs){
blogtypeservice.setBlogs(id,blogs);
}


@GetMapping
("/Type/{id}/Blog/getBlogs")
public List<Blog> getBlogs(@PathVariable(name="id") Long id){
return blogtypeservice.getBlogs(id);
}


}