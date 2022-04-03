package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmailServiceController {

 private EmailService emailservice;


@PutMapping
("/sendWithAccountTemplate")
public void sendWithAccountTemplate(@RequestParam(name = "ontvanger") Persoon ontvanger,@RequestParam(name = "password") String password){
emailservice.sendWithAccountTemplate(ontvanger,password);
}


@PutMapping
("/sendWithFormulierStaatKlaarTemplate")
public void sendWithFormulierStaatKlaarTemplate(@RequestParam(name = "ontvanger") Medewerker ontvanger){
emailservice.sendWithFormulierStaatKlaarTemplate(ontvanger);
}


@PutMapping
("/sendWithFormulierBeoordelingTemplate")
public void sendWithFormulierBeoordelingTemplate(@RequestParam(name = "ontvanger") Persoon ontvanger,@RequestParam(name = "beoordeling") OpdrachtgeverStatus beoordeling){
emailservice.sendWithFormulierBeoordelingTemplate(ontvanger,beoordeling);
}


}