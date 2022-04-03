package DTO;
 import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Box;
import domain.SocialProfile;
import domain.Sponsor;
import domain.Sponsorship;
import forms.FormObjectSponsor;
import Interface.BoxService;
import Interface.SocialProfileService;
public class SponsorService {

 private  SponsorRepository sponsorRepository;

 private  BoxService boxService;

 private  Validator validator;

 private  SocialProfileService socialProfileService;

 private  SponsorshipService sponsorshipService;

 private  MessageService messageService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public Sponsor getSponsorByUsername(String a){
    return this.sponsorRepository.getSponsorByUsername(a);
}


public Sponsor securityAndSponsor(){
    UserAccount userAccount = LoginService.getPrincipal();
    String username = userAccount.getUsername();
    Assert.notNull(this.sponsorRepository.getSponsorByUsername(username));
    Sponsor loggedSponsor = this.sponsorRepository.getSponsorByUsername(username);
    List<Authority> authorities = (List<Authority>) loggedSponsor.getUserAccount().getAuthorities();
    Assert.isTrue(authorities.get(0).toString().equals("SPONSOR"));
    return loggedSponsor;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/securityAndSponsor"))

;
Sponsor aux = restTemplate.getForObject(builder.toUriString(),Sponsor.class);
return aux;
}


}