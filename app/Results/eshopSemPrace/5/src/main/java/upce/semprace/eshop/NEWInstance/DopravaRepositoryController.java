package upce.semprace.eshop.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import java.util.*;
 import upce.semprace.eshop.entity.*;
import upce.semprace.eshop.repository.*; 
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