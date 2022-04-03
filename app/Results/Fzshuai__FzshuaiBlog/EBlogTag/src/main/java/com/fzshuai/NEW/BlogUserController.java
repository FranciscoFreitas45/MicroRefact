package com.fzshuai.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.po.Blog;
@RestController
@CrossOrigin
public class BlogUserController {

@Autowired
 private BlogUserService bloguserservice;


@PutMapping
("/User/{id}/Blog/setBlogs")
public void setBlogs(@PathVariable(name="id") Long id,@RequestBody List<Blog> blogs){
bloguserservice.setBlogs(id,blogs);
}


@GetMapping
("/User/{id}/Blog/getBlogs")
public List<Blog> getBlogs(@PathVariable(name="id") Long id){
return bloguserservice.getBlogs(id);
}


}