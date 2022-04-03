package sn.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PostRepositoryController {

 private PostRepository postrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return postrepository.findById(Object);
}


@GetMapping
("/findAllByPersonId")
public List<Post> findAllByPersonId(@RequestParam(name = "personId") long personId,@RequestParam(name = "pageable") Pageable pageable){
  return postrepository.findAllByPersonId(personId,pageable);
}


@GetMapping
("/saveAndFlush")
public Object saveAndFlush(@RequestParam(name = "Object") Object Object){
  return postrepository.saveAndFlush(Object);
}


@GetMapping
("/getTotalCountPostsByPersonId")
public int getTotalCountPostsByPersonId(@RequestParam(name = "personId") long personId){
  return postrepository.getTotalCountPostsByPersonId(personId);
}


@GetMapping
("/findAllByTextAndTime")
public List<Post> findAllByTextAndTime(@RequestParam(name = "text") String text,@RequestParam(name = "dateFrom") LocalDateTime dateFrom,@RequestParam(name = "dateTo") LocalDateTime dateTo,@RequestParam(name = "pageable") Pageable pageable){
  return postrepository.findAllByTextAndTime(text,dateFrom,dateTo,pageable);
}


@GetMapping
("/getOne")
public Object getOne(@RequestParam(name = "Object") Object Object){
  return postrepository.getOne(Object);
}


}