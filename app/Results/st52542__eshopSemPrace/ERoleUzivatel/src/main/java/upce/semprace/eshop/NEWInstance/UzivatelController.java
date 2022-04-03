package upce.semprace.eshop.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UzivatelController {

 private UzivatelRepository uzivatelrepository;


@PutMapping
("/setPrijmeni/{id}")
public void setPrijmeni(@PathVariable(name = "id") Long id,@RequestParam(name = "prijmeni") String prijmeni){
 uzivatelrepository.setPrijmeni(id,prijmeni);
}


@PutMapping
("/setAdresa/{id}")
public void setAdresa(@PathVariable(name = "id") Long id,@RequestParam(name = "adresa") String adresa){
 uzivatelrepository.setAdresa(id,adresa);
}


@PutMapping
("/setRoles/{id}")
public void setRoles(@PathVariable(name = "id") Long id,@RequestParam(name = "roles") Set<Role> roles){
 uzivatelrepository.setRoles(id,roles);
}


}