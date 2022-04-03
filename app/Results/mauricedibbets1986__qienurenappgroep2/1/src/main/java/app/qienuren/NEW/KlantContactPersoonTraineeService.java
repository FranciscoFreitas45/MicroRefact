package app.qienuren.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.controller.KlantContactPersoonRepository;
import app.qienuren.model.KlantContactPersoon;
@Service
public class KlantContactPersoonTraineeService {

@Autowired
 private KlantContactPersoonRepository klantcontactpersoonrepository;


public void setLeidingGevende(long id0KCA,KlantContactPersoon leidingGevende){
klantcontactpersoonrepository.setLeidingGevende(id0KCA,leidingGevende);
}


public KlantContactPersoon getLeidingGevende(long id0KCA){
return klantcontactpersoonrepository.getLeidingGevende(id0KCA);
}


public void koppelKlantContactPersoon(long id0KCA,KlantContactPersoon kcp){
klantcontactpersoonrepository.koppelKlantContactPersoon(id0KCA,kcp);
}


}