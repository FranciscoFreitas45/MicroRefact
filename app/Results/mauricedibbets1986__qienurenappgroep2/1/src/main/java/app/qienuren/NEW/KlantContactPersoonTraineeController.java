package app.qienuren.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.model.KlantContactPersoon;
@RestController
@CrossOrigin
public class KlantContactPersoonTraineeController {

@Autowired
 private KlantContactPersoonTraineeService klantcontactpersoontraineeservice;


@PutMapping
("/Trainee/{id}/KlantContactPersoon/setLeidingGevende")
public void setLeidingGevende(@PathVariable(name="id") long id0KCA,@RequestBody KlantContactPersoon leidingGevende){
klantcontactpersoontraineeservice.setLeidingGevende(id0KCA,leidingGevende);
}


@GetMapping
("/Trainee/{id}/KlantContactPersoon/getLeidingGevende")
public KlantContactPersoon getLeidingGevende(@PathVariable(name="id") long id0KCA){
return klantcontactpersoontraineeservice.getLeidingGevende(id0KCA);
}


@PutMapping
("/Trainee/{id}/KlantContactPersoon/koppelKlantContactPersoon")
public void koppelKlantContactPersoon(@PathVariable(name="id") long id0KCA,@RequestBody KlantContactPersoon kcp){
klantcontactpersoontraineeservice.koppelKlantContactPersoon(id0KCA,kcp);
}


}