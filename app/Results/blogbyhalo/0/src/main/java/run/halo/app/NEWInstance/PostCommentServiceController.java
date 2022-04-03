package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PostCommentServiceController {

 private PostCommentService postcommentservice;


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return postcommentservice.count(Object);
}


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return postcommentservice.create(Object);
}


@GetMapping
("/listAll")
public Object listAll(@RequestParam(name = "Object") Object Object){
  return postcommentservice.listAll(Object);
}


@GetMapping
("/createInBatch")
public Object createInBatch(@RequestParam(name = "Object") Object Object){
  return postcommentservice.createInBatch(Object);
}


@GetMapping
("/removeByPostId")
public Object removeByPostId(@RequestParam(name = "Object") Object Object){
  return postcommentservice.removeByPostId(Object);
}


@GetMapping
("/countByStatusAndPostIds")
public Object countByStatusAndPostIds(@RequestParam(name = "Object") Object Object){
  return postcommentservice.countByStatusAndPostIds(Object);
}


@GetMapping
("/countByPostId")
public Object countByPostId(@RequestParam(name = "Object") Object Object){
  return postcommentservice.countByPostId(Object);
}


@GetMapping
("/countByStatus")
public Object countByStatus(@RequestParam(name = "Object") Object Object){
  return postcommentservice.countByStatus(Object);
}


}