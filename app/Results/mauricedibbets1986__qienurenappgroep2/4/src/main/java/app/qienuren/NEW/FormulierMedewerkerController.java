package app.qienuren.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.model.Formulier;
@RestController
@CrossOrigin
public class FormulierMedewerkerController {

@Autowired
 private FormulierMedewerkerService formuliermedewerkerservice;


@GetMapping
("/Medewerker/{id}/Formulier/getArchief")
public List<Formulier> getArchief(@PathVariable(name="id") long id){
return formuliermedewerkerservice.getArchief(id);
}


@GetMapping
("/Medewerker/{id}/Formulier/getTijdelijkeFormulieren")
public List<Formulier> getTijdelijkeFormulieren(@PathVariable(name="id") long id){
return formuliermedewerkerservice.getTijdelijkeFormulieren(id);
}


@PutMapping
("/Medewerker/{id}/Formulier/verwijderFormulierUitTijdelijkeLijst")
public void verwijderFormulierUitTijdelijkeLijst(@PathVariable(name="id") long id,@RequestBody Formulier tf){
formuliermedewerkerservice.verwijderFormulierUitTijdelijkeLijst(id,tf);
}


@PutMapping
("/Medewerker/{id}/Formulier/voegFormulierToe")
public void voegFormulierToe(@PathVariable(name="id") long id,@RequestBody Formulier tf){
formuliermedewerkerservice.voegFormulierToe(id,tf);
}


@PutMapping
("/Medewerker/{id}/Formulier/voegFormulierToeAanArchief")
public void voegFormulierToeAanArchief(@PathVariable(name="id") long id,@RequestBody Formulier f){
formuliermedewerkerservice.voegFormulierToeAanArchief(id,f);
}


@PutMapping
("/Medewerker/{id}/Formulier/koppelFormulier")
public void koppelFormulier(@PathVariable(name="id") long id,@RequestBody Formulier formulierTijdelijk){
formuliermedewerkerservice.koppelFormulier(id,formulierTijdelijk);
}


@PutMapping
("/Medewerker/{id}/Formulier/setArchief")
public void setArchief(@PathVariable(name="id") long id,@RequestBody List<Formulier> archief){
formuliermedewerkerservice.setArchief(id,archief);
}


@PutMapping
("/Medewerker/{id}/Formulier/setTijdelijkeFormulieren")
public void setTijdelijkeFormulieren(@PathVariable(name="id") long id,@RequestBody List<Formulier> tijdelijkeFormulieren){
formuliermedewerkerservice.setTijdelijkeFormulieren(id,tijdelijkeFormulieren);
}


}