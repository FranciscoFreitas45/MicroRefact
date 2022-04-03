package app.qienuren.controller;
 import app.qienuren.model.KlantContactPersoon;
import app.qienuren.model.Persoon;
import app.qienuren.model.Trainee;
import app.qienuren.security.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import app.qienuren.Interface.RandomPasswordGenerator;
import app.qienuren.Interface.EmailService;
import app.qienuren.Interface.PersoonRepository;
@Service
@Transactional
public class KlantContactPersoonService {

@Autowired
 private KlantContactPersoonRepository klantContactPersoonRepository;

@Autowired
 private  PasswordEncoder passwordEncoder;

@Autowired
 private RandomPasswordGenerator randomPasswordGenerator;

@Autowired
 private EmailService emailService;

@Autowired
 private PersoonRepository persoonRepository;


public KlantContactPersoon addKlantContactPersoon(KlantContactPersoon klantContactPersoon){
    Persoon p = persoonRepository.findByEmail(klantContactPersoon.getEmail());
    if (p != null) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "email bestaat al");
    // if ( t.getEmail().equals(trainee.getEmail())) {
    // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> we zitten in de if" );
    // }
    } else {
        klantContactPersoon.setPassword(randomPasswordGenerator.generatePassayPassword());
        // zet unencoded wachtwoord in een lokale String voor email
        String nonEncodedPassword = klantContactPersoon.getPassword();
        System.out.println(klantContactPersoon.getPassword());
        // Encode password voor opslaan in database
        klantContactPersoon.setPassword(passwordEncoder.encode(klantContactPersoon.getPassword()));
        System.out.println(klantContactPersoon.getPassword());
        // Send email
        // Arguments: KCP, Subject, Message(templated?)
        // KCP fields nodig: Name, Username, Password
        emailService.sendWithAccountTemplate(klantContactPersoon, nonEncodedPassword);
        return klantContactPersoonRepository.save(klantContactPersoon);
    }
}


public Iterable<KlantContactPersoon> getAllKlantContactPersoon(){
    System.out.println("alle klant contact personen verzamelen!");
    return klantContactPersoonRepository.findAll();
}


public KlantContactPersoon getKCPById(long id){
    return klantContactPersoonRepository.findById(id).get();
}


public KlantContactPersoon kcpWachtwoordWijzigen(long id,KlantContactPersoon kcp){
    KlantContactPersoon kcp2 = klantContactPersoonRepository.findById(id).get();
    if (kcp.getPassword() != null && !kcp.getPassword().equals("")) {
        kcp2.setPassword(passwordEncoder.encode(kcp.getPassword()));
    }
    return klantContactPersoonRepository.save(kcp2);
}


}