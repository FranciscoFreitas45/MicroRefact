package app.qienuren.controller;
 import app.qienuren.model.TijdelijkeInterneMedewerker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
@Service
@Transactional
public class TijdelijkeInterneMedewerkerService {

@Autowired
 private TijdelijkeInterneMedewerkerRepository tijdelijkeInterneMedewerkerRepository;

@Autowired
 private InterneMedewerkerRepository interneMedewerkerRepository;


public TijdelijkeInterneMedewerker addTijdelijkeMedewerker(long id,TijdelijkeInterneMedewerker tijdelijkeInterneMedewerker){
    tijdelijkeInterneMedewerker.setOorspronkelijkeId(interneMedewerkerRepository.findById(id).get().getId());
    System.out.println("tijdelijke trainee aangemaakt");
    return tijdelijkeInterneMedewerkerRepository.save(tijdelijkeInterneMedewerker);
}


public TijdelijkeInterneMedewerker getTijdelijkeMedewerkerByOorspronkelijkeId(long oorspronkelijkeId){
    ArrayList<TijdelijkeInterneMedewerker> alleTijdelijkeInterneMedewerkers = (ArrayList<TijdelijkeInterneMedewerker>) tijdelijkeInterneMedewerkerRepository.findAll();
    TijdelijkeInterneMedewerker terugTeSturenTijdelijkeInterneMedewerker = null;
    for (TijdelijkeInterneMedewerker tt : alleTijdelijkeInterneMedewerkers) {
        if (tt.getOorspronkelijkeId() == oorspronkelijkeId) {
            terugTeSturenTijdelijkeInterneMedewerker = tt;
        }
    }
    return terugTeSturenTijdelijkeInterneMedewerker;
}


public TijdelijkeInterneMedewerker getTijdelijkeMedewerkerById(long tijdelijkeId){
    return tijdelijkeInterneMedewerkerRepository.findById(tijdelijkeId).get();
}


public TijdelijkeInterneMedewerker getTijdelijkeMedewerker(long id){
    return tijdelijkeInterneMedewerkerRepository.findById(id).get();
}


public Iterable<TijdelijkeInterneMedewerker> getallTijdelijkeMedewerkers(){
    System.out.println("alle tijdelijke medewerkers opgevraagd");
    return tijdelijkeInterneMedewerkerRepository.findAll();
}


}