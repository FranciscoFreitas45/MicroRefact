package es.us.isa.ideas.app.services;
 import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import es.us.isa.ideas.app.entities.Actor;
import es.us.isa.ideas.app.entities.Confirmation;
import es.us.isa.ideas.app.entities.Researcher;
import es.us.isa.ideas.app.mail.CustomMailer;
import es.us.isa.ideas.app.mail.TemplateMail;
import es.us.isa.ideas.app.repositories.ConfirmationRepository;
import es.us.isa.ideas.app.security.UserAccountService;
import javax.servlet.http.HttpServletRequest;
import es.us.isa.ideas.app.Interface.CustomMailer;
import es.us.isa.ideas.app.Interface.UserAccountService;
import es.us.isa.ideas.app.Interface.TemplateMail;
import es.us.isa.ideas.app.Interface.TemplateMail;
@Service
@Transactional
public class ConfirmationService extends BusinessService<Confirmation>{

@Autowired
 private ConfirmationRepository repository;

@Autowired
 private CustomMailer mailer;

@Autowired
 private ResearcherService researcherService;

@Autowired
 private UserAccountService userAccountService;

@Autowired
@Qualifier("registrationConfirmationTemplate")
 private TemplateMail registrationConfirmationTemplate;

@Autowired
@Qualifier("resetPasswordConfirmationTemplate")
 private TemplateMail resetPasswordConfirmationTemplate;

@Autowired
 private HttpServletRequest request;


public Confirmation resetPassword(String code){
    Confirmation confirmation = repository.getByConfirmationCode(code);
    Assert.notNull(code);
    confirmation.setConfirmationDate(new Date());
    repository.save(confirmation);
    userAccountService.resetPassword(confirmation.getResearcher().getUserAccount(), confirmation.getResearcher().getEmail());
    return confirmation;
}


public void sendPasswordResetConfirmationMail(Confirmation confirmation,Map<String,String> customization){
    Researcher researcher = confirmation.getResearcher();
    Object[] templateCustomizers = { researcher, researcher.getUserAccount() };
    Map<String, String> finalCustomizations = mailer.extractCustomizations(templateCustomizers);
    finalCustomizations.putAll(customization);
    finalCustomizations.put("$code", confirmation.getConfirmationCode());
    finalCustomizations.put("$confirmationUrl", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/confirm/");
    mailer.sendMail(researcher.getEmail(), finalCustomizations, resetPasswordConfirmationTemplate);
}


public Confirmation confirmRegistration(String code){
    try {
        Confirmation confirmation = repository.getByConfirmationCode(code);
        Assert.notNull(confirmation);
        confirmation.setConfirmationDate(new Date());
        repository.save(confirmation);
        userAccountService.create(confirmation.getResearcher());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return repository.getByConfirmationCode(code);
}


public void sendRegistrationConfirmationMail(Confirmation result,Map<String,String> customization){
    Object[] templateCustomizers = { result.getResearcher(), result };
    Map<String, String> finalCustomizations = mailer.extractCustomizations(templateCustomizers);
    finalCustomizations.putAll(customization);
    finalCustomizations.put("$confirmationUrl", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/confirm/");
    mailer.sendMail(result.getResearcher().getEmail(), finalCustomizations, registrationConfirmationTemplate);
}


@Override
public JpaRepository<Confirmation,Integer> getRepository(){
    return repository;
}


public Confirmation findByResearcherId(int id){
    return repository.getByResearcherId(id);
}


public void createPasswordResetConfirmation(String email){
    Researcher researcher = researcherService.findByEmail(email);
    Assert.notNull(researcher);
    createPasswordResetConfirmation(researcher);
}


public Confirmation createRegistrationConfirmation(Actor actor){
    Assert.notNull(actor);
    Confirmation result = new Confirmation();
    result.setRegistrationDate(new Date());
    result.setConfirmationDate(null);
    result.setResearcher((Researcher) actor);
    result.setConfirmationCode(UUID.randomUUID().toString());
    repository.save(result);
    sendRegistrationConfirmationMail(result, new HashMap<String, String>());
    return result;
}


public void delete(Confirmation rc){
    repository.delete(rc);
}


}