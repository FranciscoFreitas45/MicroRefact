package app.qienuren.controller;
 import app.qienuren.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
@Service
@Transactional
public class PersoonService {

@Autowired
 private PersoonRepository persoonRepository;

@Autowired
 private TijdelijkePersoonRepository tijdelijkePersoonRepository;


public Persoon getById(long id){
    return persoonRepository.findById(id).get();
}


public Persoon addPersoon(Persoon persoon){
    System.out.println("User aangemaakt");
    return persoonRepository.save(persoon);
}


public Persoon wijzigGegevens(long oorspronkelijkeId,long id){
    System.out.println("Verzoek gegevens wijzigen ontvangen");
    // tijdelijke trainee wordt opgehaald
    TijdelijkePersoon tijdelijkePersoon = tijdelijkePersoonRepository.findById(id).get();
    // echte trainee wordt opgehaald
    Persoon persoon = persoonRepository.findById(oorspronkelijkeId).get();
    System.out.println("voor: tijdTrainee>>> " + tijdelijkePersoon.getNaam());
    System.out.println("voor: tijdTrainee>>> " + tijdelijkePersoon.getTelefoonnr());
    System.out.println("voor: trainee>>> " + persoon.getNaam());
    System.out.println("voor: trainee>>> " + persoon.getTelefoonnr());
    // echte trainee krijgt waardes van de tijdelijke trainee, tenzij niets is ingevuld
    if (tijdelijkePersoon.getNaam().isEmpty()) {
        persoon.setNaam(persoon.getNaam());
    } else {
        persoon.setNaam(tijdelijkePersoon.getNaam());
    }
    if (tijdelijkePersoon.getEmail().isEmpty()) {
        persoon.setEmail(persoon.getEmail());
    } else {
        persoon.setEmail(tijdelijkePersoon.getEmail());
    }
    if (tijdelijkePersoon.getTelefoonnr().isEmpty()) {
        persoon.setTelefoonnr(persoon.getTelefoonnr());
    } else {
        persoon.setTelefoonnr(tijdelijkePersoon.getTelefoonnr());
    }
    if (tijdelijkePersoon.getPostcode().isEmpty()) {
        persoon.setPostcode(persoon.getPostcode());
    } else {
        persoon.setPostcode(tijdelijkePersoon.getPostcode());
    }
    if (tijdelijkePersoon.getStraatNaamNr().isEmpty()) {
        persoon.setStraatNaamNr(persoon.getStraatNaamNr());
    } else {
        persoon.setStraatNaamNr(tijdelijkePersoon.getStraatNaamNr());
    }
    if (tijdelijkePersoon.getWoonplaats().isEmpty()) {
        persoon.setWoonplaats(persoon.getWoonplaats());
    } else {
        persoon.setWoonplaats(tijdelijkePersoon.getWoonplaats());
    }
    System.out.println("na: tijdTrainee>>> " + tijdelijkePersoon.getNaam());
    System.out.println("na: tijdTrainee>>> " + tijdelijkePersoon.getTelefoonnr());
    System.out.println("na: trainee>>> " + persoon.getNaam());
    System.out.println("na: trainee>>> " + persoon.getTelefoonnr());
    // aangepaste gegevens worden opgeslagen in de database
    tijdelijkePersoonRepository.deleteById(tijdelijkePersoon.getId());
    return persoonRepository.save(persoon);
}


public Iterable<Persoon> getAllMedewerkers(){
    System.out.println("Alle medewerkers opgevraagd");
    ArrayList<Persoon> prullenBak = (ArrayList) persoonRepository.findAll();
    ArrayList<Persoon> tempList = new ArrayList<>();
    for (Persoon p : prullenBak) {
        if (!(p instanceof KlantContactPersoon)) {
            tempList.add(p);
        }
    }
    return tempList;
}


}