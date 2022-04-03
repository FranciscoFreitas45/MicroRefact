package main.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PostController {

 private PostRepository postrepository;


@PutMapping
("/addPostComment/{id}")
public void addPostComment(@PathVariable(name = "id") int id,@RequestParam(name = "postComment") PostComment postComment){
 postrepository.addPostComment(id,postComment);
}


}