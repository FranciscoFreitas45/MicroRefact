package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BasePostServiceController {

 private BasePostService basepostservice;


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return basepostservice.count(Object);
}


@PutMapping
("/increaseVisit")
public void increaseVisit(@RequestParam(name = "postId") Integer postId){
basepostservice.increaseVisit(postId);
}


}