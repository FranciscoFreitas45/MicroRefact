package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DomainEntityController {

 private DomainEntity domainentity;

 private DomainEntity domainentity;


@PutMapping
("/setVersion")
public void setVersion(@RequestParam(name = "version") int version){
domainentity.setVersion(version);
}


}