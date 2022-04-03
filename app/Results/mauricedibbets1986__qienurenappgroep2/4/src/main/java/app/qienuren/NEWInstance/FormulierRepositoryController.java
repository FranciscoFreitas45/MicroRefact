package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FormulierRepositoryController {

 private FormulierRepository formulierrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return formulierrepository.save(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return formulierrepository.findById(Object);
}


}