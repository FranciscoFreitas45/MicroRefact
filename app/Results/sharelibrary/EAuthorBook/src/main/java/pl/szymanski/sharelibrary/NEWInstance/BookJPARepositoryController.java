package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BookJPARepositoryController {

 private BookJPARepository bookjparepository;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return bookjparepository.findAll(Object);
}


@GetMapping
("/saveAndFlush")
public Object saveAndFlush(@RequestParam(name = "Object") Object Object){
  return bookjparepository.saveAndFlush(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return bookjparepository.findById(Object);
}


@GetMapping
("/findByTitleIsContainingIgnoreCase")
public List<Book> findByTitleIsContainingIgnoreCase(@RequestParam(name = "title") String title){
  return bookjparepository.findByTitleIsContainingIgnoreCase(title);
}


@GetMapping
("/findByUserId")
public List<Book> findByUserId(@RequestParam(name = "userId") Long userId){
  return bookjparepository.findByUserId(userId);
}


}