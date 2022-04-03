package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import com.sda.inTeams.service.*;
 import java.util.*;
 import com.sda.inTeams.model.Comment.*;
  import com.sda.inTeams.exception.InvalidOperation;


@RestController
@CrossOrigin
public class CommentServiceController {

 private CommentService commentservice;


@GetMapping
("/getAllUserComments")
public List<Comment> getAllUserComments(@RequestParam(name = "userId") long userId){
  try {
  return commentservice.getAllUserComments(userId);
  }catch(InvalidOperation e){
    return null;
  }
}


@GetMapping
("/getAll")
public List<Comment> getAll(){
  return commentservice.getAll();
}


}