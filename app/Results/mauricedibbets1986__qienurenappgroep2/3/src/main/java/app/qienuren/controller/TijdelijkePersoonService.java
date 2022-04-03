package app.qienuren.controller;
 import app.qienuren.model.TijdelijkePersoon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
@Service
@Transactional
public class TijdelijkePersoonService {

@Autowired
 private TijdelijkePersoonRepository tijdelijkePersoonRepository;

@Autowired
 private PersoonRepository persoonRepository;


public Iterable<TijdelijkePersoon> getallTijdelijkePersonen(){
    return tijdelijkePersoonRepository.findAll();
}


public TijdelijkePersoon getById(long oorspronkelijkeId){
    ArrayList<TijdelijkePersoon> alleTijdelijkePersonen = (ArrayList<TijdelijkePersoon>) tijdelijkePersoonRepository.findAll();
    TijdelijkePersoon terugTeSturenTijdelijkePersoon = null;
    for (TijdelijkePersoon tt : alleTijdelijkePersonen) {
        if (tt.getOorspronkelijkeId() == oorspronkelijkeId) {
            terugTeSturenTijdelijkePersoon = tt;
        }
    }
    return terugTeSturenTijdelijkePersoon;
}


public void deleteTijdelijkePersoonById(long id){
    System.out.println("tijdelijke wijziging is verwijderd");
    tijdelijkePersoonRepository.deleteById(id);
}


}