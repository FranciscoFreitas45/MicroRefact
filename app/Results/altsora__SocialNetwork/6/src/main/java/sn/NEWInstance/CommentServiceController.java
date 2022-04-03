package sn.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CommentServiceController {

 private CommentService commentservice;


@GetMapping
("/getCommentsByPostId")
public List<CommentResponse> getCommentsByPostId(@RequestParam(name = "postId") long postId){
  return commentservice.getCommentsByPostId(postId);
}


}