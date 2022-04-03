package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PostMetaServiceController {

 private PostMetaService postmetaservice;


@GetMapping
("/listAll")
public Object listAll(@RequestParam(name = "Object") Object Object){
  return postmetaservice.listAll(Object);
}


@GetMapping
("/createInBatch")
public Object createInBatch(@RequestParam(name = "Object") Object Object){
  return postmetaservice.createInBatch(Object);
}


@GetMapping
("/listBy")
public Object listBy(@RequestParam(name = "Object") Object Object){
  return postmetaservice.listBy(Object);
}


@GetMapping
("/removeByPostId")
public Object removeByPostId(@RequestParam(name = "Object") Object Object){
  return postmetaservice.removeByPostId(Object);
}


@GetMapping
("/listPostMetaAsMap")
public Object listPostMetaAsMap(@RequestParam(name = "Object") Object Object){
  return postmetaservice.listPostMetaAsMap(Object);
}


@GetMapping
("/convertToMap")
public Object convertToMap(@RequestParam(name = "Object") Object Object){
  return postmetaservice.convertToMap(Object);
}


@GetMapping
("/convertTo")
public Object convertTo(@RequestParam(name = "Object") Object Object){
  return postmetaservice.convertTo(Object);
}


@GetMapping
("/createOrUpdateByPostId")
public Object createOrUpdateByPostId(@RequestParam(name = "Object") Object Object){
  return postmetaservice.createOrUpdateByPostId(Object);
}


}