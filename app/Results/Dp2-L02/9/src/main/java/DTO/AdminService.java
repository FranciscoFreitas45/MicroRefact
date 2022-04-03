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
import DTO.AreaService;
import DTO.ParadeService;
import DTO.UserAccount;
import DTO.Authority;
import DTO.FinderService;
import DTO.MemberService;
import DTO.RequestService;
import DTO.HistoryService;
import DTO.SponsorshipService;
import DTO.SocialProfile;
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


public void loggedAsAdmin(){
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    List<Authority> authorities = (List<Authority>) userAccount.getAuthorities();
    Assert.isTrue(authorities.get(0).toString().equals("ADMIN"));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedAsAdmin"))

;
restTemplate.put(builder.toUriString(),null);
}


}