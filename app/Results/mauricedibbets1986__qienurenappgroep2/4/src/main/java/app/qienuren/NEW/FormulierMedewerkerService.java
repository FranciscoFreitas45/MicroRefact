package app.qienuren.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.controller.FormulierRepository;
import app.qienuren.model.Formulier;
@Service
public class FormulierMedewerkerService {

@Autowired
 private FormulierRepository formulierrepository;


public List<Formulier> getArchief(long id){
return formulierrepository.getArchief(id);
}


public List<Formulier> getTijdelijkeFormulieren(long id){
return formulierrepository.getTijdelijkeFormulieren(id);
}


public void verwijderFormulierUitTijdelijkeLijst(long id,Formulier tf){
formulierrepository.verwijderFormulierUitTijdelijkeLijst(id,tf);
}


public void voegFormulierToe(long id,Formulier tf){
formulierrepository.voegFormulierToe(id,tf);
}


public void voegFormulierToeAanArchief(long id,Formulier f){
formulierrepository.voegFormulierToeAanArchief(id,f);
}


public void koppelFormulier(long id,Formulier formulierTijdelijk){
formulierrepository.koppelFormulier(id,formulierTijdelijk);
}


public void setArchief(long id,List<Formulier> archief){
formulierrepository.setArchief(id,archief);
}


public void setTijdelijkeFormulieren(long id,List<Formulier> tijdelijkeFormulieren){
formulierrepository.setTijdelijkeFormulieren(id,tijdelijkeFormulieren);
}


}