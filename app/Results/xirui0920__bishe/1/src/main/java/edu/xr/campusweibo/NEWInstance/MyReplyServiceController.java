package edu.xr.campusweibo.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MyReplyServiceController {

 private MyReplyService myreplyservice;


@GetMapping
("/getAllReply")
public List<MyReply> getAllReply(@RequestParam(name = "id") Long id){
  return myreplyservice.getAllReply(id);
}


}