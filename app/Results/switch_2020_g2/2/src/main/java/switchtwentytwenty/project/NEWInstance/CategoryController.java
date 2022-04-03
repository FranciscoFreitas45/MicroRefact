package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CategoryController {

 private Category category;

 private Category category;


@GetMapping
("/isStandard")
public boolean isStandard(){
  return category.isStandard();
}


}