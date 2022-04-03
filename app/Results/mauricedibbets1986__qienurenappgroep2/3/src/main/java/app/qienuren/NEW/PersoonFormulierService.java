package app.qienuren.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.controller.PersoonRepository;
import app.qienuren.model.Persoon;
@Service
public class PersoonFormulierService {

@Autowired
 private PersoonRepository persoonrepository;


public void setMedewerker(long idN8E9,Persoon medewerker){
persoonrepository.setMedewerker(idN8E9,medewerker);
}


public Persoon getMedewerker(long idN8E9){
return persoonrepository.getMedewerker(idN8E9);
}


}