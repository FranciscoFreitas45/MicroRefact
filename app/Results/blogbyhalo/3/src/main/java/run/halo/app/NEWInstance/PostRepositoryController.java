package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PostRepositoryController {

 private PostRepository postrepository;


@GetMapping
("/getById")
public Object getById(@RequestParam(name = "Object") Object Object){
  return postrepository.getById(Object);
}


@GetMapping
("/findAllById")
public Object findAllById(@RequestParam(name = "Object") Object Object){
  return postrepository.findAllById(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return postrepository.findById(Object);
}


}