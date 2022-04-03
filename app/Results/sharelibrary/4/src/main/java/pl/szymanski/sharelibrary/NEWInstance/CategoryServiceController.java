package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CategoryServiceController {

 private CategoryService categoryservice;


@GetMapping
("/findByName")
public Category findByName(@RequestParam(name = "name") String name){
  return categoryservice.findByName(name);
}


}