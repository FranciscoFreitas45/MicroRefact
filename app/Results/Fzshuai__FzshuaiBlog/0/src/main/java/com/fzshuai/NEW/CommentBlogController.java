package com.fzshuai.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.po.Comment;
@RestController
@CrossOrigin
public class CommentBlogController {

@Autowired
 private CommentBlogService commentblogservice;


@GetMapping
("/Blog/{id}/Comment/getComments")
public List<Comment> getComments(@PathVariable(name="id") Long id){
return commentblogservice.getComments(id);
}


@PutMapping
("/Blog/{id}/Comment/setComments")
public void setComments(@PathVariable(name="id") Long id,@RequestBody List<Comment> comments){
commentblogservice.setComments(id,comments);
}


}