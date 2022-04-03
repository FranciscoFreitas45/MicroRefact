package upce.semprace.eshop.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProduktRepositoryController {

 private ProduktRepository produktrepository;


@GetMapping
("/findById")
public Optional<Produkt> findById(@RequestParam(name = "id") Long id){
  return produktrepository.findById(id);
}


}