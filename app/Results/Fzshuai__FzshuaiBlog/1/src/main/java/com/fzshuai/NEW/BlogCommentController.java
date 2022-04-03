package com.fzshuai.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.po.Blog;
@RestController
@CrossOrigin
public class BlogCommentController {

@Autowired
 private BlogCommentService blogcommentservice;


@GetMapping
("/Comment/{id}/Blog/getBlog")
public Blog getBlog(@PathVariable(name="id") Long id){
return blogcommentservice.getBlog(id);
}


@PutMapping
("/Comment/{id}/Blog/setBlog")
public void setBlog(@PathVariable(name="id") Long id,@RequestBody Blog blog){
blogcommentservice.setBlog(id,blog);
}


}