package app.qienuren.controller;
 import app.qienuren.model;
import app.qienuren.security.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import app.qienuren.Interface.BedrijfRepository;
import app.qienuren.Interface.KlantContactPersoonRepository;
import app.qienuren.Interface.FormulierRepository;
import app.qienuren.Interface.EmailService;
import app.qienuren.Interface.MedewerkerService;
import app.qienuren.Interface.KlantContactPersoonRepository;
@Service
@Transactional
public class TraineeService {

@Autowired
 private TraineeRepository traineeRepository;

@Autowired
 private BedrijfRepository bedrijfRepository;

@Autowired
 private KlantContactPersoonRepository kcpRepository;

@Autowired
 private FormulierRepository formulierRepository;

@Autowired
 private TijdelijkeTraineeRepository tijdelijkeTraineeRepository;

@Autowired
 private  PasswordEncoder passwordEncoder;

@Autowired
 private RandomPasswordGenerator randomPasswordGenerator;

@Autowired
 private EmailService emailService;

@Autowired
 private MedewerkerService medewerkerService;

@Autowired
 private PersoonRepository persoonRepository;


public Trainee traineeWachtwoordWijzigen(long traineeID,Trainee trainee){
    Trainee trainee2 = traineeRepository.findById(traineeID).get();
    if (trainee.getPassword() != null && !trainee.getPassword().equals("")) {
        trainee2.setPassword(passwordEncoder.encode(trainee.getPassword()));
    }
    return traineeRepository.save(trainee2);
}


public Iterable<Trainee> getAllTrainees(){
    System.out.println("Alle trainees opgevraagd");
    return traineeRepository.findAll();
}


public Iterable<Trainee> getTraineesByKCPId(long kcpId){
    Iterable<Trainee> trainees = traineeRepository.findAll();
    ArrayList<Trainee> traineesBehorendAanKCP = new ArrayList<>();
    for (Trainee t : trainees) {
        if (t.getLeidingGevende() != null) {
            if (t.getLeidingGevende().getId() == kcpId) {
                traineesBehorendAanKCP.add(t);
            }
        }
    }
    return traineesBehorendAanKCP;
}


public Trainee addTrainee(Trainee trainee){
    Persoon p = persoonRepository.findByEmail(trainee.getEmail());
    if (p != null) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "email bestaat al");
    // if ( t.getEmail().equals(trainee.getEmail())) {
    // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> we zitten in de if" );
    // }
    } else {
        trainee.setPassword(randomPasswordGenerator.generatePassayPassword());
        // zet unencoded wachtwoord in een lokale String voor email
        String nonEncodedPassword = trainee.getPassword();
        System.out.println(trainee.getPassword());
        // Encode password voor opslaan in database
        trainee.setPassword(passwordEncoder.encode(trainee.getPassword()));
        System.out.println(trainee.getPassword());
        // Send email
        // Arguments: Trainee, Subject, Message(templated?)
        // Trainee fields nodig: Name, Username, Password
        emailService.sendWithAccountTemplate(trainee, nonEncodedPassword);
        // Klaarzetten formulier van de  huidige maand
        ArrayList<Formulier> nieuwFormulier = new ArrayList<>();
        Formulier formulier = new Formulier(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        nieuwFormulier.add(formulier);
        // medewerkerService.genereerLeegFormulier(trainee);
        trainee.setTijdelijkeFormulieren(nieuwFormulier);
        formulierRepository.save(formulier);
        return traineeRepository.save(trainee);
    }
}


public Trainee traineeKoppelformulier(long traineeID,long formulierid){
    System.out.println("Trainee aan formulier gekoppeld");
    Formulier formulierTijdelijk = formulierRepository.findById(formulierid).get();
    Trainee traineeTijdelijk = traineeRepository.findById(traineeID).get();
    traineeTijdelijk.koppelFormulier(formulierTijdelijk);
    return traineeRepository.save(traineeTijdelijk);
}


public Trainee traineeKoppelContactPersoon(long traineeID,long kcpID){
    System.out.println("Trainee gekoppeld aan Bedrijf");
    KlantContactPersoon tijdelijkKCP = kcpRepository.findById(kcpID).get();
    Trainee tijdelijkTrainee = traineeRepository.findById(traineeID).get();
    tijdelijkTrainee.koppelKlantContactPersoon(tijdelijkKCP);
    tijdelijkKCP.koppelTrainee(tijdelijkTrainee);
    kcpRepository.save(tijdelijkKCP);
    return traineeRepository.save(tijdelijkTrainee);
}


public Trainee wijzigGegevens(long oorspronkelijkeId,long id){
    System.out.println("Verzoek gegevens wijzigen ontvangen");
    // tijdelijke trainee wordt opgehaald
    TijdelijkeTrainee tijdtrainee = tijdelijkeTraineeRepository.findById(id).get();
    // echte trainee wordt opgehaald
    Trainee trainee = traineeRepository.findById(oorspronkelijkeId).get();
    System.out.println("voor: tijdTrainee>>> " + tijdtrainee.getNaam());
    System.out.println("voor: tijdTrainee>>> " + tijdtrainee.getTelefoonnr());
    System.out.println("voor: trainee>>> " + trainee.getNaam());
    System.out.println("voor: trainee>>> " + trainee.getTelefoonnr());
    // echte trainee krijgt waardes van de tijdelijke trainee, tenzij niets is ingevuld
    if (tijdtrainee.getNaam().isEmpty()) {
        trainee.setNaam(trainee.getNaam());
    } else {
        trainee.setNaam(tijdtrainee.getNaam());
    }
    if (tijdtrainee.getEmail().isEmpty()) {
        trainee.setEmail(trainee.getEmail());
    } else {
        trainee.setEmail(tijdtrainee.getEmail());
    }
    if (tijdtrainee.getTelefoonnr().isEmpty()) {
        trainee.setTelefoonnr(trainee.getTelefoonnr());
    } else {
        trainee.setTelefoonnr(tijdtrainee.getTelefoonnr());
    }
    if (tijdtrainee.getPostcode().isEmpty()) {
        trainee.setPostcode(trainee.getPostcode());
    } else {
        trainee.setPostcode(tijdtrainee.getPostcode());
    }
    if (tijdtrainee.getStraatNaamNr().isEmpty()) {
        trainee.setStraatNaamNr(trainee.getStraatNaamNr());
    } else {
        trainee.setStraatNaamNr(tijdtrainee.getStraatNaamNr());
    }
    if (tijdtrainee.getWoonplaats().isEmpty()) {
        trainee.setWoonplaats(trainee.getWoonplaats());
    } else {
        trainee.setWoonplaats(tijdtrainee.getWoonplaats());
    }
    System.out.println("na: tijdTrainee>>> " + tijdtrainee.getNaam());
    System.out.println("na: tijdTrainee>>> " + tijdtrainee.getTelefoonnr());
    System.out.println("na: trainee>>> " + trainee.getNaam());
    System.out.println("na: trainee>>> " + trainee.getTelefoonnr());
    // aangepaste gegevens worden opgeslagen in de database
    return traineeRepository.save(trainee);
}


public Trainee getTraineeById(long id){
    System.out.println("Trainee opgehaald");
    return traineeRepository.findById(id).get();
}


}