package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EntCustomerRepositoryController {

 private EntCustomerRepository entcustomerrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return entcustomerrepository.save(Object);
}


}