package es.us.isa.ideas.app.security;
 import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.us.isa.ideas.app.controllers.FileController;
import es.us.isa.ideas.app.controllers.WorkspaceController;
import es.us.isa.ideas.app.entities.Actor;
import es.us.isa.ideas.app.entities.Researcher;
import es.us.isa.ideas.app.entities.Workspace;
import es.us.isa.ideas.app.mail.CustomMailer;
import es.us.isa.ideas.app.mail.TemplateMail;
import es.us.isa.ideas.app.repositories.WorkspaceRepository;
import es.us.isa.ideas.app.services.BusinessService;
import es.us.isa.ideas.app.services.ResearcherService;
import es.us.isa.ideas.repo.IdeasRepo;
import es.us.isa.ideas.repo.exception.AuthenticationException;
import es.us.isa.ideas.repo.impl.fs.FSFacade;
import es.us.isa.ideas.repo.impl.fs.FSWorkspace;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import es.us.isa.ideas.app.Interface.ResearcherService;
import es.us.isa.ideas.app.Interface.WorkspaceRepository;
import es.us.isa.ideas.app.Interface.CustomMailer;
import es.us.isa.ideas.app.Interface.TemplateMail;
import es.us.isa.ideas.app.Interface.TemplateMail;
import es.us.isa.ideas.app.DTO.Actor;
@Service
@Transactional
public class UserAccountService extends BusinessService<UserAccount>{

 public  String USERNAME_DISAMBIGUATION_SUFIX;

 public  Authority DEFAULT_AUTHORITY;

@Autowired
 private UserAccountRepository userRepository;

@Autowired
 private ResearcherService researcherService;

@Autowired
 private WorkspaceRepository workspaceRepository;

@Autowired
 private CustomMailer mailer;

@Autowired
 private ConnectionRepository connectionRepository;

@Autowired
@Qualifier("registrationDoneTemplate")
 private TemplateMail confirmationDoneTemplate;

@Autowired
@Qualifier("resetPasswordDoneTemplate")
 private TemplateMail resetPasswordDoneTemplate;


public void resetPassword(UserAccount account,String notificationEmail){
    String password = UUID.randomUUID().toString();
    Md5PasswordEncoder encoder = new Md5PasswordEncoder();
    account.setPassword(encoder.encodePassword(password, null));
    userRepository.save(account);
    if (notificationEmail != null && !notificationEmail.isEmpty()) {
        sendPasswordResetEmail(notificationEmail, account, password);
    }
}


public void createDemoWorkspace(String username){
    System.out.println("### Creating demo WS for " + username);
    FSWorkspace demoWS = new FSWorkspace("SampleWorkspace", "DemoMaster");
    FSWorkspace newWS = new FSWorkspace("SampleWorkspace", username);
    try {
        FileController.initRepoLab();
        IdeasRepo.get().getRepo().move(demoWS, newWS, true);
    } catch (AuthenticationException e) {
        System.out.println("### Error creating demo WS for " + username);
        e.printStackTrace();
    }
}


public UserAccount findByUsername(String username){
    return userRepository.findByUsername(username);
}


public void modifyPassword(UserAccount userAccount,String oldPass,String newPass){
    Md5PasswordEncoder encoder = new Md5PasswordEncoder();
    String passhash = encoder.encodePassword(oldPass, null);
    String newpasshash = encoder.encodePassword(newPass, null);
    UserAccount oldUserAccount = userRepository.findByUsername(userAccount.getUsername());
    if (passhash.equals(oldUserAccount.getPassword())) {
        userAccount.setPassword(newpasshash);
    // userRepository.save(userAccount); // Fallo con candado
    } else {
        throw new InvalidParameterException("The value of the old password is wrong.");
    }
}


public String generateUsername(Actor actor){
    String result = actor.getEmail().substring(0, actor.getEmail().indexOf("@"));
    while (findByUsername(result) != null) {
        result += USERNAME_DISAMBIGUATION_SUFIX;
    }
    return result;
}


@Override
public JpaRepository<UserAccount,Integer> getRepository(){
    return userRepository;
}


public void save(UserAccount userAccount){
    userRepository.saveAndFlush(userAccount);
}


public UserAccount create(Actor actor){
    return create(actor.getUserAccount().getId(), generateUsername(actor), UUID.randomUUID().toString(), actor.getEmail());
}


public Collection<UserAccount> findAll(){
    return userRepository.findAll();
}


public void delete(UserAccount account){
    userRepository.delete(account);
}


public void sendPasswordResetEmail(String email,UserAccount account,String password){
    Object[] templateCustomizers = { account };
    Map<String, String> finalCustomizations = mailer.extractCustomizations(templateCustomizers);
    finalCustomizations.put("$password", password);
    mailer.sendMail(email, finalCustomizations, resetPasswordDoneTemplate);
}


public void sendWelcomeMail(String email,UserAccount account,String password){
    Object[] templateCustomizers = { account };
    Map<String, String> finalCustomizations = mailer.extractCustomizations(templateCustomizers);
    finalCustomizations.put("$password", password);
    mailer.sendMail(email, finalCustomizations, confirmationDoneTemplate);
}


}