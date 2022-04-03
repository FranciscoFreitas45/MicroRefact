package com.ITBlog.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BlogServiceController {

 private BlogService blogservice;


@GetMapping
("/findBlogById")
public Blog findBlogById(@RequestParam(name = "blogId") long blogId){
  return blogservice.findBlogById(blogId);
}


}