package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICategoryRepositoryController {

 private ICategoryRepository icategoryrepository;


@GetMapping
("/save")
public Category save(@RequestParam(name = "category") Category category){
  return icategoryrepository.save(category);
}


@GetMapping
("/findByID")
public Category findByID(@RequestParam(name = "id") CategoryID id){
  return icategoryrepository.findByID(id);
}


}