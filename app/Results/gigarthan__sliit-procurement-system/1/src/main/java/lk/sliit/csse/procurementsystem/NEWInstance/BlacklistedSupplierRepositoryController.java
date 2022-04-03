package lk.sliit.csse.procurementsystem.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BlacklistedSupplierRepositoryController {

 private BlacklistedSupplierRepository blacklistedsupplierrepository;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return blacklistedsupplierrepository.findAll(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return blacklistedsupplierrepository.save(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return blacklistedsupplierrepository.deleteById(Object);
}


}