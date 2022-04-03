package upce.semprace.eshop.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import java.util.*;
 import upce.semprace.eshop.entity.*;
 import upce.semprace.eshop.repository.*;
 
@RestController
@CrossOrigin
public class ProduktRepositoryController {

 private ProduktRepository produktrepository;


@GetMapping
("/findById")
public Optional<Produkt> findById(@RequestParam(name = "id") Long id){
  return produktrepository.findById(id);
}

@GetMapping
("/save")
public Produkt save(@RequestParam(name = "produkt") Produkt produkt){
  return produktrepository.save(produkt);
}


}