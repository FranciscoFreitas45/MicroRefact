package restock.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProveidorBusinessController {

 private ProveidorBusiness proveidorbusiness;


@GetMapping
("/getProveidorsPerOrganitzacio")
public List<Proveidor> getProveidorsPerOrganitzacio(@RequestParam(name = "orgId") Integer orgId){
  return proveidorbusiness.getProveidorsPerOrganitzacio(orgId);
}


}