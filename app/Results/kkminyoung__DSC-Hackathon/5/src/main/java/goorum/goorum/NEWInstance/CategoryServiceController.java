package goorum.goorum.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CategoryServiceController {

 private CategoryService categoryservice;


@GetMapping
("/getList")
public List<Category> getList(){
  return categoryservice.getList();
}


}