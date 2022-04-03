package main.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TagRepositoryController {

 private TagRepository tagrepository;


@GetMapping
("/findByName")
public List<String> findByName(@RequestParam(name = "query") String query){
  return tagrepository.findByName(query);
}


@GetMapping
("/findNamesOfTags")
public List<String> findNamesOfTags(){
  return tagrepository.findNamesOfTags();
}


@GetMapping
("/getOne")
public Object getOne(@RequestParam(name = "Object") Object Object){
  return tagrepository.getOne(Object);
}


}