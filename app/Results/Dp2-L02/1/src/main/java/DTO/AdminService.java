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
import Interface.ParadeService;
import Interface.RequestService;
import Interface.AreaService;
import Interface.PositionService;
import Interface.FinderService;
import Interface.MemberService;
import Interface.HistoryService;
import Interface.SponsorshipService;
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

  String url = "http://5";


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


public Admin loggedAdmin(){
    Admin admin = new Admin();
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    admin = this.adminRepository.getAdminByUsername(userAccount.getUsername());
    return admin;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedAdmin"))

;
Admin aux = restTemplate.getForObject(builder.toUriString(),Admin.class);
return aux;
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


public Admin updateAdmin(Admin admin){
    this.loggedAsAdmin();
    Assert.isTrue(admin.getId() != 0 && this.loggedAdmin().getId() == admin.getId());
    return this.adminRepository.save(admin);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateAdmin"))

.queryParam("admin",admin)
;
Admin aux = restTemplate.getForObject(builder.toUriString(),Admin.class);
return aux;
}


}