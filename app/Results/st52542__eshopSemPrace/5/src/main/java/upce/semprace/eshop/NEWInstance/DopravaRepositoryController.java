package upce.semprace.eshop.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DopravaRepositoryController {

 private DopravaRepository dopravarepository;


@GetMapping
("/findById")
public Optional<Doprava> findById(@RequestParam(name = "id") Long id){
  return dopravarepository.findById(id);
}


}