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
import domain.Box;
import domain.Brotherhood;
import domain.Enrolment;
import domain.Finder;
import domain.Member;
import domain.Parade;
import domain.Request;
import domain.SocialProfile;
import forms.FormObjectMember;
import repositories.MemberRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import Interface.BoxService;
import Interface.MessageService;
import Interface.ParadeService;
import Interface.RequestService;
import Interface.EnrolmentService;
import Interface.BrotherhoodService;
import Interface.ActorService;
public class MemberService {

 private  MemberRepository memberRepository;

 private  FinderService finderService;

 private  BoxService boxService;

 private  Validator validator;

 private  MessageService messageService;

 private  ParadeService paradeService;

 private  RequestService requestService;

 private  EnrolmentService enrolmentService;

 private  BrotherhoodService brotherhoodService;

 private  ActorService actorService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public Member getMemberByUsername(String username){
    return this.memberRepository.getMemberByUsername(username);
}


public Member reconstruct(Member member,BindingResult binding){
    Member result;
    Member pururu;
    result = member;
    pururu = this.memberRepository.findOne(member.getId());
    result.setUserAccount(pururu.getUserAccount());
    result.setBoxes(pururu.getBoxes());
    result.setHasSpam(pururu.getHasSpam());
    result.setSocialProfiles(pururu.getSocialProfiles());
    result.setPolarity(pururu.getPolarity());
    result.setEnrolments(pururu.getEnrolments());
    result.setFinder(pururu.getFinder());
    result.setRequests(pururu.getRequests());
    this.validator.validate(result, binding);
    return result;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstruct"))

.queryParam("member",member)
.queryParam("binding",binding)
;
Member aux = restTemplate.getForObject(builder.toUriString(),Member.class);
return aux;
}


public Member updateMember(Member member){
    this.loggedAsMember();
    Assert.isTrue(member.getId() != 0 && this.loggedMember().getId() == member.getId());
    return this.memberRepository.save(member);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateMember"))

.queryParam("member",member)
;
Member aux = restTemplate.getForObject(builder.toUriString(),Member.class);
return aux;
}


public Member loggedMember(){
    Member m = new Member();
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    m = this.memberRepository.getMemberByUsername(userAccount.getUsername());
    return m;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedMember"))

;
Member aux = restTemplate.getForObject(builder.toUriString(),Member.class);
return aux;
}


}