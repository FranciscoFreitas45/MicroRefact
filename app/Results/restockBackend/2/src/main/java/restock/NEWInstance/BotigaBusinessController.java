package restock.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BotigaBusinessController {

 private BotigaBusiness botigabusiness;


@GetMapping
("/getBotiguesPerOrganitzacio")
public List<Botiga> getBotiguesPerOrganitzacio(@RequestParam(name = "orgId") Integer orgId){
  return botigabusiness.getBotiguesPerOrganitzacio(orgId);
}


@GetMapping
("/getBotigaOfResponsable")
public Botiga getBotigaOfResponsable(@RequestParam(name = "usuari") Usuari usuari){
  return botigabusiness.getBotigaOfResponsable(usuari);
}


}