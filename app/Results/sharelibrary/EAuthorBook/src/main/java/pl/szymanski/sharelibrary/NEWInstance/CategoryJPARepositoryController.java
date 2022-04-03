package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CategoryJPARepositoryController {

 private CategoryJPARepository categoryjparepository;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return categoryjparepository.findAll(Object);
}


@GetMapping
("/findFirstByNameIgnoreCase")
public Optional<Category> findFirstByNameIgnoreCase(@RequestParam(name = "name") String name){
  return categoryjparepository.findFirstByNameIgnoreCase(name);
}


}