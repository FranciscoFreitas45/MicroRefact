package gov.cdc.nccdphp.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DivisionRepositoryController {

 private DivisionRepository divisionrepository;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return divisionrepository.findAll(Object);
}


}