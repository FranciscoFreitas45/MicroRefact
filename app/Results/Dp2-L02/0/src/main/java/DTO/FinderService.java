package DTO;
 import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import domain.Finder;
import domain.Member;
import domain.Parade;
import repositories.FinderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
public class FinderService {

 private  FinderRepository finderRepository;

 private  MemberService memberRepository;

 private  Validator validator;

 private  ConfigurationService configurationService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public List<Parade> getAllPublishedParades(){
    return this.finderRepository.getPublushedParades();
}


public Finder getCurrentFinder(){
    UserAccount userAccount = LoginService.getPrincipal();
    Member loggedMember = this.memberRepository.getMemberByUsername(userAccount.getUsername());
    return loggedMember.getFinder();
}


public List<Finder> findAll(){
    return this.finderRepository.findAll();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))

;
List<Finder> aux = restTemplate.getForObject(builder.toUriString(),List<Finder>.class);
return aux;
}


}