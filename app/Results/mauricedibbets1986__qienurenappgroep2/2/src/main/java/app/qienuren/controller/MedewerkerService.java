package app.qienuren.controller;
 import app.qienuren.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import app.qienuren.Interface.TraineeService;
import app.qienuren.Interface.FormulierService;
import app.qienuren.Interface.TraineeRepository;
import app.qienuren.Interface.EmailService;
@Configuration
@EnableScheduling
@Service
@Transactional
public class MedewerkerService {

@Autowired
 private TraineeService ts;

@Autowired
 private InterneMedewerkerService ims;

@Autowired
 private FormulierService fs;

@Autowired
 private TraineeRepository traineeRepository;

@Autowired
 private MedewerkerRepository medewerkerRepository;

@Autowired
 private EmailService emailService;

 private  List<Medewerker> medewerkers;

 private  List<Trainee> trainees;

 private  List<InterneMedewerker> interneMedewerkers;


public ArrayList<Medewerker> voegTraineesEnInterneMedewerkersSamen(){
    medewerkers = new ArrayList<>();
    trainees = (List) ts.getAllTrainees();
    interneMedewerkers = (List) ims.getAllInterneMedewerkers();
    for (Trainee t : trainees) {
        medewerkers.add(t);
    }
    for (InterneMedewerker i : interneMedewerkers) {
        if (!(i.getType() == MedewerkerType.Admin)) {
            medewerkers.add(i);
        }
    }
    return (ArrayList<Medewerker>) medewerkers;
}


public void genereerLeegFormulierVoorAlleMedewerkers(){
    ArrayList<Medewerker> deMedewerkers = voegTraineesEnInterneMedewerkersSamen();
    for (Medewerker m : deMedewerkers) {
        m.voegFormulierToe(fs.addNieuwFormulier(new Formulier(LocalDate.now().getMonthValue(), LocalDate.now().getYear())));
        // Send email
        // Arguments: Medewerker, Subject, Message(templated?)
        // Medewerker fields nodig: Name, ?
        emailService.sendWithFormulierStaatKlaarTemplate(m);
    }
}


public Medewerker getMedewerkerById(long id){
    System.out.println("Medewerker opgehaald - test Maandag");
    return medewerkerRepository.findById(id).get();
}


}