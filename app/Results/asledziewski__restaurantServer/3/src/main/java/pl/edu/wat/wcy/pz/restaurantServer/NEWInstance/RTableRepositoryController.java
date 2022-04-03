package pl.edu.wat.wcy.pz.restaurantServer.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RTableRepositoryController {

 private RTableRepository rtablerepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return rtablerepository.findById(Object);
}


}