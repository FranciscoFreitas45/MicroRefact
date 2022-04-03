package com.dtdhehe.ptulife.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CommentServiceController {

 private CommentService commentservice;


@GetMapping
("/getHotByComment")
public Integer getHotByComment(@RequestParam(name = "pid") String pid){
  return commentservice.getHotByComment(pid);
}


}