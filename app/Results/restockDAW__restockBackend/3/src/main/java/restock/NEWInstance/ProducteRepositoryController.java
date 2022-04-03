package restock.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProducteRepositoryController {

 private ProducteRepository producterepository;


@GetMapping
("/findById")
public Producte findById(@RequestParam(name = "id") Integer id){
  return producterepository.findById(id);
}


}