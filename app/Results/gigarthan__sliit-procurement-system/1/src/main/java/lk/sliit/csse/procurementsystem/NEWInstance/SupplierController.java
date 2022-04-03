package lk.sliit.csse.procurementsystem.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SupplierController {

 private Supplier supplier;


@GetMapping
("/setBlackListed")
public Object setBlackListed(@RequestParam(name = "Object") Object Object){
  return supplier.setBlackListed(Object);
}


@GetMapping
("/isBlackListed")
public Object isBlackListed(@RequestParam(name = "Object") Object Object){
  return supplier.isBlackListed(Object);
}


@GetMapping
("/getName")
public Object getName(@RequestParam(name = "Object") Object Object){
  return supplier.getName(Object);
}


}