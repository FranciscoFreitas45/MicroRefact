package DTO;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.SponsorshipRepository;
import domain.Actor;
import domain.Configuration;
import domain.CreditCard;
import domain.Message;
import domain.Parade;
import domain.ParadeStatus;
import domain.Sponsor;
import domain.Sponsorship;
import forms.FormObjectSponsorshipCreditCard;
import Interface.ConfigurationService;
import Interface.AdminService;
import Interface.MessageService;
import Interface.ActorService;
import DTO.AdminService;
import DTO.ConfigurationService;
import DTO.Configuration;
import DTO.MessageService;
import DTO.ActorService;
public class SponsorshipService {

 private  SponsorshipRepository sponsorshipRepository;

 private  ConfigurationService configurationService;

 private  Validator validator;

 private  SponsorService sponsorService;

 private  CreditCardService creditCardService;

 private  AdminService adminService;

 private  MessageService messageService;

 private  ActorService actorService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public Collection<Sponsorship> getSponsorships(Boolean isActivated){
    Sponsor sponsor = this.sponsorService.securityAndSponsor();
    Collection<Sponsorship> sponsorships;
    if (isActivated == null)
        sponsorships = this.sponsorshipRepository.getAllSponsorshipsOfSponsor(sponsor.getId());
    else if (isActivated == true)
        sponsorships = this.sponsorshipRepository.getActivatedSponsorshipsOfSponsor(sponsor.getId());
    else
        sponsorships = this.sponsorshipRepository.getDeactivatedSponsorshipsOfSponsor(sponsor.getId());
    return sponsorships;
}


public Sponsor getSponsorOfSponsorship(int sponsorshipId){
    return this.sponsorshipRepository.getSponsorOfSponsorship(sponsorshipId);
}


public Collection<Sponsorship> getAllSponsorshipsAsAdmin(){
    return this.sponsorshipRepository.getAllSponsorshipsAsAdmin();
}


public Collection<Sponsorship> getDeactivatedSponsorshipsAsAdmin(){
    return this.sponsorshipRepository.getDeactivatedSponsorshipsAsAdmin();
}


public Collection<Sponsorship> getDeactivatedSponsorshipsOfSponsor(int sponsorId){
    return this.sponsorshipRepository.getDeactivatedSponsorshipsOfSponsor(sponsorId);
}


public Collection<Sponsorship> getAllSponsorshipsOfSponsor(int sponsorId){
    return this.sponsorshipRepository.getAllSponsorshipsOfSponsor(sponsorId);
}


public Sponsorship getRandomSponsorship(int paradeId){
    List<Sponsorship> sponsorships = this.getActivatedSponsorshipsOfParade(paradeId);
    Random random = new Random();
    if (sponsorships.size() == 0)
        return this.createSponsorship();
    else
        return sponsorships.get(random.nextInt(sponsorships.size()));
}


public Collection<Sponsorship> getActivatedSponsorshipsOfSponsor(int sponsorId){
    return this.sponsorshipRepository.getActivatedSponsorshipsOfSponsor(sponsorId);
}


public List<Sponsorship> getActivatedSponsorshipsOfParade(int paradeId){
    return this.sponsorshipRepository.getActivatedSponsorshipsOfParade(paradeId);
}


public List<Sponsorship> getSponsorshipsOfParade(int paradeId){
    return this.sponsorshipRepository.getSponsorshipsOfParade(paradeId);
}


public Collection<Sponsorship> getActivatedSponsorshipsAsAdmin(){
    return this.sponsorshipRepository.getActivatedSponsorshipsAsAdmin();
}


public Collection<Sponsorship> getSponsorshipsAsAdmin(Boolean isActivated){
    this.adminService.loggedAsAdmin();
    Collection<Sponsorship> sponsorships;
    if (isActivated == null)
        sponsorships = this.sponsorshipRepository.getAllSponsorshipsAsAdmin();
    else if (isActivated == true)
        sponsorships = this.sponsorshipRepository.getActivatedSponsorshipsAsAdmin();
    else
        sponsorships = this.sponsorshipRepository.getDeactivatedSponsorshipsAsAdmin();
    return sponsorships;
}


public List<Sponsorship> findAll(){
    return this.sponsorshipRepository.findAll();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))

;
List<Sponsorship> aux = restTemplate.getForObject(builder.toUriString(),List<Sponsorship>.class);
return aux;
}


}