package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CommentServiceController {

 private CommentService commentservice;


@GetMapping
("/getAllUserComments")
public List<Comment> getAllUserComments(@RequestParam(name = "userId") long userId){
  return commentservice.getAllUserComments(userId);
}


@GetMapping
("/getAll")
public List<Comment> getAll(){
  return commentservice.getAll();
}


}