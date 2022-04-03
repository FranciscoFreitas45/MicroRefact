package app.qienuren.controller;
 import app.qienuren.model.Bedrijf;
import app.qienuren.model.KlantContactPersoon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import app.qienuren.Interface.TraineeRepository;
@Service
@Transactional
public class BedrijfService {

@Autowired
 private BedrijfRepository bedrijfRepository;

@Autowired
 private TraineeRepository traineeRepository;

@Autowired
 private KlantContactPersoonRepository kcpRepository;


public Bedrijf addBedrijf(Bedrijf bedrijf){
    if (bedrijfRepository.findByEmail(bedrijf.getEmail()).isPresent()) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "email bestaat al");
    }
    System.out.println("Bedrijf aangemaakt");
    return bedrijfRepository.save(bedrijf);
}


public Iterable<Bedrijf> getAllBedrijf(){
    System.out.println("alle bedrijven opgevraagd");
    return bedrijfRepository.findAll();
}


public Bedrijf klantContactPersoonToevoegenBedrijf(long kcpID,long bedrijfID){
    System.out.println("Trainee gekoppeld aan Bedrijf");
    KlantContactPersoon tijdelijkKCP = kcpRepository.findById(kcpID).get();
    Bedrijf tijdelijkBedrijf = bedrijfRepository.findById(bedrijfID).get();
    tijdelijkBedrijf.koppelContactPersoon(tijdelijkKCP);
    tijdelijkKCP.koppelBedrijf(tijdelijkBedrijf);
    kcpRepository.save(tijdelijkKCP);
    return bedrijfRepository.save(tijdelijkBedrijf);
}


public Bedrijf getBedrijfbyID(long bedrijfsId){
    return bedrijfRepository.findById(bedrijfsId).get();
}


}