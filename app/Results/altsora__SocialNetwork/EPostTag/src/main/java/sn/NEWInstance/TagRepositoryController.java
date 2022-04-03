package sn.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TagRepositoryController {

 private TagRepository tagrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return tagrepository.save(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return tagrepository.findById(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return tagrepository.deleteById(Object);
}


}