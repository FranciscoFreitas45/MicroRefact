package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CommentServiceController {

 private CommentService commentservice;


@GetMapping
("/findALlCommentDetailByTime")
public List<CommentDetail> findALlCommentDetailByTime(){
  return commentservice.findALlCommentDetailByTime();
}


}