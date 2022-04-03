package DTO;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import domain.Actor;
import domain.Admin;
import domain.Area;
import domain.Box;
import domain.Brotherhood;
import domain.Chapter;
import domain.Member;
import domain.Message;
import domain.Parade;
import domain.Position;
import domain.SocialProfile;
import domain.Sponsor;
import forms.FormObjectMember;
import repositories.AdminRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import Interface.MessageService;
import Interface.ActorService;
import Interface.BoxService;
import Interface.ParadeService;
import Interface.RequestService;
import Interface.AreaService;
import Interface.PositionService;
import Interface.FinderService;
import Interface.MemberService;
import Interface.HistoryService;
import Interface.SponsorshipService;
import DTO.AreaService;
import DTO.ActorService;
import DTO.BoxService;
import DTO.Box;
import DTO.ParadeService;
import DTO.FinderService;
import DTO.MemberService;
import DTO.RequestService;
import DTO.HistoryService;
import DTO.SponsorshipService;
import DTO.SocialProfile;
import DTO.Message;
import DTO.MessageService;
import DTO.PositionService;
public class AdminService {

 private  AdminRepository adminRepository;

 private  MessageService messageService;

 private  ActorService actorService;

 private  BoxService boxService;

 private  ParadeService paradeService;

 private  RequestService requestService;

 private  AreaService areaService;

 private  PositionService positionService;

 private  Validator validator;

 private  FinderService finderService;

 private  MemberService memberService;

 private  HistoryService historyService;

 private  SponsorshipService sponsorshipService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public Admin getSystem(){
    return this.adminRepository.getSystem();
}


public Admin getAdminByUsername(String username){
    return this.adminRepository.getAdminByUsername(username);
}


public Float getRatioNonSpammers(){
    return this.adminRepository.getRatioNonSpammers();
}


public Float getRatioSpammers(){
    return this.adminRepository.getRatioSpammers();
}


public void unBanSuspiciousActor(Actor a){
    this.loggedAsAdmin();
    a.getUserAccount().setIsNotLocked(true);
    this.actorService.save(a);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/unBanSuspiciousActor"))

.queryParam("a",a)
;
restTemplate.put(builder.toUriString(),null);
}


public void banSuspiciousActor(Actor a){
    this.loggedAsAdmin();
    a.getUserAccount().setIsNotLocked(false);
    this.actorService.save(a);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/banSuspiciousActor"))

.queryParam("a",a)
;
restTemplate.put(builder.toUriString(),null);
}


public Admin reconstruct(Admin admin,BindingResult binding){
    Admin result;
    Admin pururu;
    result = admin;
    pururu = this.adminRepository.findOne(admin.getId());
    result.setUserAccount(pururu.getUserAccount());
    result.setBoxes(pururu.getBoxes());
    result.setHasSpam(pururu.getHasSpam());
    result.setSocialProfiles(pururu.getSocialProfiles());
    result.setPolarity(pururu.getPolarity());
    /*
		 * result.setName(admin.getName()); result.setMiddleName(admin.getMiddleName());
		 * result.setSurname(admin.getSurname()); result.setPhoto(admin.getPhoto());
		 * result.setEmail(admin.getEmail());
		 * result.setPhoneNumber(admin.getPhoneNumber());
		 * result.setAddress(admin.getAddress());
		 */
    this.validator.validate(result, binding);
    /*
		 * try {
		 *
		 * } catch (Throwable oops) { System.out.println("LOL EKISDE"); }
		 *
		 * System.out.println(binding); System.out.println("LOL");
		 */
    return result;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstruct"))

.queryParam("admin",admin)
.queryParam("binding",binding)
;
Admin aux = restTemplate.getForObject(builder.toUriString(),Admin.class);
return aux;
}


public void broadcastMessage(Message message){
    this.loggedAsAdmin();
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    Actor admin = this.actorService.getActorByUsername(userAccount.getUsername());
    List<Actor> actors = new ArrayList<Actor>();
    actors = this.actorService.findAll();
    for (Actor a : actors) if (!(a.equals(admin))) {
        message.setReceiver(a);
        this.messageService.sendMessageBroadcasted(message);
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/broadcastMessage"))

.queryParam("message",message)
;
restTemplate.put(builder.toUriString(),null);
}


}