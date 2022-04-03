package restock.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProveidorRepositoryController {

 private ProveidorRepository proveidorrepository;


@GetMapping
("/findById")
public Proveidor findById(@RequestParam(name = "id") Integer id){
  return proveidorrepository.findById(id);
}


}