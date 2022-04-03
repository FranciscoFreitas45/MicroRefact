package sn.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PostController {

 private Post post;

 private Post post;


@GetMapping
("/isBlocked")
public boolean isBlocked(){
  return post.isBlocked();
}


}