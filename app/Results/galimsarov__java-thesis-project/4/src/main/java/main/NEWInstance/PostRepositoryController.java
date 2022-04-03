package main.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PostRepositoryController {

 private PostRepository postrepository;


@GetMapping
("/getCountOfPostsForModeration")
public int getCountOfPostsForModeration(@RequestParam(name = "moderatorId") int moderatorId){
  return postrepository.getCountOfPostsForModeration(moderatorId);
}


@GetMapping
("/getOne")
public Object getOne(@RequestParam(name = "Object") Object Object){
  return postrepository.getOne(Object);
}


@GetMapping
("/saveAndFlush")
public Object saveAndFlush(@RequestParam(name = "Object") Object Object){
  return postrepository.saveAndFlush(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return postrepository.findAll(Object);
}


@GetMapping
("/getPostsForTheYear")
public List<String> getPostsForTheYear(@RequestParam(name = "year") String year){
  return postrepository.getPostsForTheYear(year);
}


@GetMapping
("/getPostsCountOfUser")
public int getPostsCountOfUser(@RequestParam(name = "userId") int userId){
  return postrepository.getPostsCountOfUser(userId);
}


@GetMapping
("/getLikesCountOfUsersPosts")
public int getLikesCountOfUsersPosts(@RequestParam(name = "userId") int userId){
  return postrepository.getLikesCountOfUsersPosts(userId);
}


@GetMapping
("/getDisLikesCountOfUsersPosts")
public int getDisLikesCountOfUsersPosts(@RequestParam(name = "userId") int userId){
  return postrepository.getDisLikesCountOfUsersPosts(userId);
}


@GetMapping
("/getViewsCountOfUsersPosts")
public int getViewsCountOfUsersPosts(@RequestParam(name = "userId") int userId){
  return postrepository.getViewsCountOfUsersPosts(userId);
}


@GetMapping
("/getFirstPostOfUser")
public Date getFirstPostOfUser(@RequestParam(name = "userId") int userId){
  return postrepository.getFirstPostOfUser(userId);
}


@GetMapping
("/getTime")
public Object getTime(@RequestParam(name = "Object") Object Object){
  return postrepository.getTime(Object);
}


@GetMapping
("/getPostsCount")
public int getPostsCount(){
  return postrepository.getPostsCount();
}


@GetMapping
("/getLikesCount")
public int getLikesCount(){
  return postrepository.getLikesCount();
}


@GetMapping
("/getDisLikesCount")
public int getDisLikesCount(){
  return postrepository.getDisLikesCount();
}


@GetMapping
("/getViewsCount")
public int getViewsCount(){
  return postrepository.getViewsCount();
}


@GetMapping
("/getFirstPost")
public Date getFirstPost(){
  return postrepository.getFirstPost();
}


}