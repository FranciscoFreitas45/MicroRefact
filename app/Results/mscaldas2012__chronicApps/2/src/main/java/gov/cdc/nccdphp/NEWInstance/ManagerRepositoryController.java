package gov.cdc.nccdphp.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ManagerRepositoryController {

 private ManagerRepository managerrepository;


@GetMapping
("/findByActiveTrue")
public List<Manager> findByActiveTrue(){
  return managerrepository.findByActiveTrue();
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return managerrepository.findAll(Object);
}


}