package lk.sliit.csse.procurementsystem.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SupplierRepositoryController {

 private SupplierRepository supplierrepository;


@GetMapping
("/findByBlackListedFalse")
public List<Supplier> findByBlackListedFalse(){
  return supplierrepository.findByBlackListedFalse();
}


@GetMapping
("/setBlackListedFor")
public int setBlackListedFor(@RequestParam(name = "isBlacklisted") Boolean isBlacklisted,@RequestParam(name = "name") String name){
  return supplierrepository.setBlackListedFor(isBlacklisted,name);
}


}