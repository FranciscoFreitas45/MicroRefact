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


public Sponsor loggedSponsor(){
    Sponsor brother = new Sponsor();
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    brother = this.sponsorRepository.getSponsorByUsername(userAccount.getUsername());
    return brother;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedSponsor"))

;
Sponsor aux = restTemplate.getForObject(builder.toUriString(),Sponsor.class);
return aux;
}


public Sponsor reconstructSponsor(Sponsor Sponsor,BindingResult binding){
    Sponsor result;
    Sponsor pururu;
    result = Sponsor;
    pururu = this.sponsorRepository.findOne(Sponsor.getId());
    result.setUserAccount(pururu.getUserAccount());
    result.setBoxes(pururu.getBoxes());
    result.setHasSpam(pururu.getHasSpam());
    result.setSocialProfiles(pururu.getSocialProfiles());
    result.setPolarity(pururu.getPolarity());
    result.setSponsorships(pururu.getSponsorships());
    this.validator.validate(result, binding);
    return result;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstructSponsor"))

.queryParam("Sponsor",Sponsor)
.queryParam("binding",binding)
;
Sponsor aux = restTemplate.getForObject(builder.toUriString(),Sponsor.class);
return aux;
}


public Sponsor save(Sponsor h){
    return this.sponsorRepository.save(h);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))

.queryParam("h",h)
;
Sponsor aux = restTemplate.getForObject(builder.toUriString(),Sponsor.class);
return aux;
}


}