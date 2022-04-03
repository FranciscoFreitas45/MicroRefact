package app.qienuren.controller;
 import app.qienuren.model;
import app.qienuren.security.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import app.qienuren.Interface.RandomPasswordGenerator;
import app.qienuren.Interface.EmailService;
import app.qienuren.Interface.PersoonRepository;
import app.qienuren.Interface.FormulierRepository;
@Service
@Transactional
public class InterneMedewerkerService {

@Autowired
 private InterneMedewerkerRepository interneMedewerkerRepository;

@Autowired
 private  PasswordEncoder passwordEncoder;

@Autowired
 private RandomPasswordGenerator randomPasswordGenerator;

@Autowired
 private EmailService emailService;

@Autowired
 private TijdelijkeInterneMedewerkerRepository tijdelijkeInterneMedewerkerRepository;

@Autowired
 private PersoonRepository persoonRepository;

@Autowired
 private FormulierRepository formulierRepository;


public Iterable<InterneMedewerker> getAllInterneMedewerkers(){
    System.out.println("Alle interne medewerkers opgevraagd");
    return interneMedewerkerRepository.findAll();
}


public InterneMedewerker internemedewerkerWachtwoordWijzigen(long id,InterneMedewerker interneMedewerker){
    InterneMedewerker interneMedewerker1 = interneMedewerkerRepository.findById(id).get();
    if (interneMedewerker.getPassword() != null && !interneMedewerker.getPassword().equals("")) {
        interneMedewerker1.setPassword(passwordEncoder.encode(interneMedewerker.getPassword()));
    }
    return interneMedewerkerRepository.save(interneMedewerker1);
}


public InterneMedewerker addInterneMederwerker(InterneMedewerker interneMedewerker){
    Persoon p = persoonRepository.findByEmail(interneMedewerker.getEmail());
    if (p != null) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "email bestaat al");
    // if ( t.getEmail().equals(trainee.getEmail())) {
    // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> we zitten in de if" );
    // }
    } else {
        interneMedewerker.setPassword(randomPasswordGenerator.generatePassayPassword());
        // zet unencoded wachtwoord in een lokale String voor email
        String nonEncodedPassword = interneMedewerker.getPassword();
        System.out.println(interneMedewerker.getPassword());
        // Encode password voor opslaan in database
        interneMedewerker.setPassword(passwordEncoder.encode(interneMedewerker.getPassword()));
        System.out.println(interneMedewerker.getPassword());
        // Send email
        // Arguments: InterneMedewerker, Subject, Message(templated?)
        // InterneMedewerker fields nodig: Name, Username, Password
        emailService.sendWithAccountTemplate(interneMedewerker, nonEncodedPassword);
        // Klaarzetten formulier van de  huidige maand
        ArrayList<Formulier> nieuwFormulier = new ArrayList<>();
        Formulier formulier = new Formulier(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        nieuwFormulier.add(formulier);
        // medewerkerService.genereerLeegFormulier(trainee);
        interneMedewerker.setTijdelijkeFormulieren(nieuwFormulier);
        formulierRepository.save(formulier);
        return interneMedewerkerRepository.save(interneMedewerker);
    }
}


public InterneMedewerker wijzigGegevens(long oorspronkelijkeId,long id){
    System.out.println("Verzoek gegevens wijzigen ontvangen");
    // tijdelijke trainee wordt opgehaald
    TijdelijkeInterneMedewerker tijdelijkeInterneMedewerker = tijdelijkeInterneMedewerkerRepository.findById(id).get();
    // echte trainee wordt opgehaald
    InterneMedewerker interneMedewerker = interneMedewerkerRepository.findById(oorspronkelijkeId).get();
    System.out.println("voor: tijdTrainee>>> " + tijdelijkeInterneMedewerker.getNaam());
    System.out.println("voor: tijdTrainee>>> " + tijdelijkeInterneMedewerker.getTelefoonnr());
    System.out.println("voor: trainee>>> " + interneMedewerker.getNaam());
    System.out.println("voor: trainee>>> " + interneMedewerker.getTelefoonnr());
    // echte trainee krijgt waardes van de tijdelijke trainee, tenzij niets is ingevuld
    if (tijdelijkeInterneMedewerker.getNaam().isEmpty() | tijdelijkeInterneMedewerker.getNaam() == null) {
        interneMedewerker.setNaam(interneMedewerker.getNaam());
    } else {
        interneMedewerker.setNaam(tijdelijkeInterneMedewerker.getNaam());
    }
    if (tijdelijkeInterneMedewerker.getEmail().isEmpty() | tijdelijkeInterneMedewerker.getEmail() == null) {
        interneMedewerker.setEmail(interneMedewerker.getEmail());
    } else {
        interneMedewerker.setEmail(tijdelijkeInterneMedewerker.getEmail());
    }
    if (tijdelijkeInterneMedewerker.getTelefoonnr().isEmpty() | tijdelijkeInterneMedewerker.getTelefoonnr() == null) {
        interneMedewerker.setTelefoonnr(interneMedewerker.getTelefoonnr());
    } else {
        interneMedewerker.setTelefoonnr(tijdelijkeInterneMedewerker.getTelefoonnr());
    }
    if (tijdelijkeInterneMedewerker.getPostcode().isEmpty() | tijdelijkeInterneMedewerker.getPostcode() == null) {
        interneMedewerker.setPostcode(interneMedewerker.getPostcode());
    } else {
        interneMedewerker.setPostcode(tijdelijkeInterneMedewerker.getPostcode());
    }
    if (tijdelijkeInterneMedewerker.getStraatNaamNr().isEmpty() | tijdelijkeInterneMedewerker.getStraatNaamNr() == null) {
        interneMedewerker.setStraatNaamNr(tijdelijkeInterneMedewerker.getStraatNaamNr());
    } else {
        interneMedewerker.setStraatNaamNr(tijdelijkeInterneMedewerker.getStraatNaamNr());
    }
    if (tijdelijkeInterneMedewerker.getWoonplaats().isEmpty() | tijdelijkeInterneMedewerker.getWoonplaats() == null) {
        interneMedewerker.setWoonplaats(interneMedewerker.getWoonplaats());
    } else {
        interneMedewerker.setWoonplaats(tijdelijkeInterneMedewerker.getWoonplaats());
    }
    System.out.println("na: tijdTrainee>>> " + tijdelijkeInterneMedewerker.getNaam());
    System.out.println("na: tijdTrainee>>> " + tijdelijkeInterneMedewerker.getTelefoonnr());
    System.out.println("na: trainee>>> " + interneMedewerker.getNaam());
    System.out.println("na: trainee>>> " + interneMedewerker.getTelefoonnr());
    // aangepaste gegevens worden opgeslagen in de database
    return interneMedewerkerRepository.save(interneMedewerker);
}


public InterneMedewerker getInterneMedewerkerById(long id){
    System.out.println("Interne medewerker opgehaald");
    return interneMedewerkerRepository.findById(id).get();
}


}