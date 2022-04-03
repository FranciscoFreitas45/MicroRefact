package es.us.isa.ideas.app.services;
 import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import es.us.isa.ideas.app.entities.Actor;
import es.us.isa.ideas.app.entities.Confirmation;
import es.us.isa.ideas.app.entities.Researcher;
import es.us.isa.ideas.app.repositories.ResearcherRepository;
import es.us.isa.ideas.app.security.Authority;
import es.us.isa.ideas.app.security.LoginService;
import es.us.isa.ideas.app.security.UserAccount;
import es.us.isa.ideas.app.security.UserAccountService;
import es.us.isa.ideas.app.Interface.ResearcherRepository;
import es.us.isa.ideas.app.Interface.UserAccountService;
import es.us.isa.ideas.app.DTO.Authority;
@Service
@Transactional(readOnly = false)
public class ResearcherService extends BusinessService<Researcher>{

@Inject
 private  ResearcherRepository researcherRepository;

@Inject
 private  UserAccountService userAccountService;

@Inject
 private  ConfirmationService registrationConfirmationService;


public void saveNew(Researcher researcher){
    Assert.notNull(researcher);
    // System.out.println("############ Researcher" + researcher.getAddress()+ " "+researcher.getName()+ " "+researcher.getPhone()
    // + " "+researcher.getEmail()+ " "+researcher.getId());
    try {
        Researcher r = researcherRepository.findByEmail(researcher.getEmail());
        if (r == null) {
            Researcher new_r = researcherRepository.save(researcher);
            registrationConfirmationService.createRegistrationConfirmation((Actor) new_r);
        } else {
            throw new Throwable("email repeated");
        }
    } catch (Throwable oops) {
        System.out.println("############### : " + oops);
        throw new Throwable("email repeated");
    }
}


public Researcher createNewResearcher(){
    Researcher newResearcher = new Researcher();
    UserAccount account = new UserAccount();
    Collection<Authority> autorities = new ArrayList<Authority>();
    autorities.add(Authority.get(Authority.RESEARCHER));
    account.setAuthorities(autorities);
    newResearcher.setUserAccount(account);
    return newResearcher;
}


public Researcher findByUserName(String username){
    assert username != null;
    Researcher result;
    result = researcherRepository.findByUsername(username);
    assert result != null;
    return result;
}


@Override
public JpaRepository<Researcher,Integer> getRepository(){
    return researcherRepository;
}


public Researcher findByUserAccount(UserAccount userAccount){
    assert userAccount != null;
    Researcher result;
    result = researcherRepository.findByUserAccountId(userAccount.getId());
    assert result != null;
    return result;
}


public void save(Researcher researcher){
    Assert.notNull(researcher);
    Assert.notNull(researcher.getUserAccount());
    researcherRepository.save(researcher);
}


public String simplify(String name){
    return name.trim().toLowerCase().replaceAll("[ \n\r\t]", "");
}


public Researcher findByPrincipal(){
    Researcher result;
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    assert userAccount != null;
    result = findByUserAccount(userAccount);
    assert result != null;
    return result;
}


public Researcher findByEmail(String email){
    return researcherRepository.findByEmail(email);
}


public Collection<Researcher> findAll(){
    return researcherRepository.findAll();
}


public void delete(Researcher researcher){
    Confirmation rc = registrationConfirmationService.findByResearcherId(researcher.getId());
    registrationConfirmationService.delete(rc);
    researcherRepository.delete(researcher);
}


}