package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class KlantContactPersoonServiceController {

 private KlantContactPersoonService klantcontactpersoonservice;


@GetMapping
("/getKCPById")
public KlantContactPersoon getKCPById(@RequestParam(name = "id") long id){
  return klantcontactpersoonservice.getKCPById(id);
}


@GetMapping
("/kcpWachtwoordWijzigen")
public KlantContactPersoon kcpWachtwoordWijzigen(@RequestParam(name = "id") long id,@RequestParam(name = "kcp") KlantContactPersoon kcp){
  return klantcontactpersoonservice.kcpWachtwoordWijzigen(id,kcp);
}


}