package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CategoryRepositoryController {

 private CategoryRepository categoryrepository;


@GetMapping
("/findByName")
public Optional<Category> findByName(@RequestParam(name = "name") String name){
  return categoryrepository.findByName(name);
}


}