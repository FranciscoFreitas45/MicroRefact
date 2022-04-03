package com.ITBlog.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CommentServiceController {

 private CommentService commentservice;


@GetMapping
("/deleteComment")
public int deleteComment(@RequestParam(name = "commentId") long commentId){
  return commentservice.deleteComment(commentId);
}


}