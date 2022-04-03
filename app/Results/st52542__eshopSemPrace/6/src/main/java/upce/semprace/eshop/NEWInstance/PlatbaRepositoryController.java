package upce.semprace.eshop.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PlatbaRepositoryController {

 private PlatbaRepository platbarepository;


@GetMapping
("/findById")
public Optional<Platba> findById(@RequestParam(name = "id") Long id){
  return platbarepository.findById(id);
}


}