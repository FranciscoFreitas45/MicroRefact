package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class KlantContactPersoonRepositoryController {

 private KlantContactPersoonRepository klantcontactpersoonrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return klantcontactpersoonrepository.findById(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return klantcontactpersoonrepository.save(Object);
}


}