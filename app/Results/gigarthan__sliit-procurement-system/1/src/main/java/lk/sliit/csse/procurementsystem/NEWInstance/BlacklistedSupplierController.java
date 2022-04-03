package lk.sliit.csse.procurementsystem.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BlacklistedSupplierController {

 private BlacklistedSupplier blacklistedsupplier;


@GetMapping
("/setSupplier")
public Object setSupplier(@RequestParam(name = "Object") Object Object){
  return blacklistedsupplier.setSupplier(Object);
}


@GetMapping
("/getSupplier")
public Object getSupplier(@RequestParam(name = "Object") Object Object){
  return blacklistedsupplier.getSupplier(Object);
}


@GetMapping
("/getId")
public Object getId(@RequestParam(name = "Object") Object Object){
  return blacklistedsupplier.getId(Object);
}


}