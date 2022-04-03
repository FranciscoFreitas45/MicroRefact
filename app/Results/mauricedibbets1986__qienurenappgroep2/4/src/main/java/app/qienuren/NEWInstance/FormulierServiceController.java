package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FormulierServiceController {

 private FormulierService formulierservice;


@GetMapping
("/updateFormulier")
public Formulier updateFormulier(@RequestParam(name = "nieuwF") Formulier nieuwF){
  return formulierservice.updateFormulier(nieuwF);
}


@GetMapping
("/getAlleFormulierenVoorAdmin")
public Iterable<Formulier> getAlleFormulierenVoorAdmin(){
  return formulierservice.getAlleFormulierenVoorAdmin();
}


@GetMapping
("/AdminStatusGoed")
public Formulier AdminStatusGoed(@RequestParam(name = "formulierid") long formulierid,@RequestParam(name = "medewerkerid") long medewerkerid){
  return formulierservice.AdminStatusGoed(formulierid,medewerkerid);
}


@GetMapping
("/AdminStatusFout")
public Formulier AdminStatusFout(@RequestParam(name = "id") long id,@RequestParam(name = "medewerkerid") long medewerkerid){
  return formulierservice.AdminStatusFout(id,medewerkerid);
}


@GetMapping
("/addNieuwFormulier")
public Formulier addNieuwFormulier(@RequestParam(name = "formulier") Formulier formulier){
  return formulierservice.addNieuwFormulier(formulier);
}


}