package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TenantRepositoryController {

 private TenantRepository tenantrepository;


@GetMapping
("/findById")
public Tenant findById(@RequestParam(name = "id") String id){
  return tenantrepository.findById(id);
}


}