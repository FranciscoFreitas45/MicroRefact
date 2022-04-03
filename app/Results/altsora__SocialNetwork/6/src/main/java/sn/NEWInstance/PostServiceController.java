package sn.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PostServiceController {

 private PostService postservice;


@GetMapping
("/findAllByPersonId")
public List<Post> findAllByPersonId(@RequestParam(name = "personId") long personId,@RequestParam(name = "offset") int offset,@RequestParam(name = "itemPerPage") int itemPerPage){
  return postservice.findAllByPersonId(personId,offset,itemPerPage);
}


@GetMapping
("/getExistsWallPost")
public WallPostResponse getExistsWallPost(@RequestParam(name = "post") Post post,@RequestParam(name = "author") PersonResponse author,@RequestParam(name = "comments") List<CommentResponse> comments){
  return postservice.getExistsWallPost(post,author,comments);
}


@GetMapping
("/getTotalCountPostsByPersonId")
public int getTotalCountPostsByPersonId(@RequestParam(name = "personId") long personId){
  return postservice.getTotalCountPostsByPersonId(personId);
}


@GetMapping
("/addPost")
public Post addPost(@RequestParam(name = "author") Person author,@RequestParam(name = "title") String title,@RequestParam(name = "text") String text,@RequestParam(name = "postTime") LocalDateTime postTime){
  return postservice.addPost(author,title,text,postTime);
}


@GetMapping
("/createNewWallPost")
public WallPostResponse createNewWallPost(@RequestParam(name = "post") Post post,@RequestParam(name = "author") PersonResponse author){
  return postservice.createNewWallPost(post,author);
}


@PutMapping
("/incLikesCount")
public void incLikesCount(@RequestParam(name = "postId") long postId){
postservice.incLikesCount(postId);
}


@PutMapping
("/decLikesCount")
public void decLikesCount(@RequestParam(name = "postId") long postId){
postservice.decLikesCount(postId);
}


}