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
import DTO.SocialProfile;
import DTO.FormObjectSponsor;
import DTO.Authority;
import DTO.UserAccount;
import DTO.BoxService;
import DTO.SocialProfileService;
import DTO.Box;
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


public void deleteSponsor(){
    this.loggedAsSponsor();
    Sponsor sponsor = this.loggedSponsor();
    this.boxService.deleteAllBoxes();
    this.messageService.updateSendedMessageByLogguedActor();
    this.messageService.updateReceivedMessageToLogguedActor();
    this.socialProfileService.deleteAllSocialProfiles();
    this.sponsorshipService.deleteAllSponsorships();
    this.delete(sponsor);
    this.flush();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteSponsor"))

;
restTemplate.put(builder.toUriString(),null);
}


}