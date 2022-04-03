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
import DTO.SocialProfile;
import DTO.BoxService;
import DTO.Box;
import DTO.MessageService;
import DTO.ParadeService;
import DTO.RequestService;
import DTO.BrotherhoodService;
import DTO.EnrolmentService;
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


public List<Member> findAll(){
    return this.memberRepository.findAll();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))

;
List<Member> aux = restTemplate.getForObject(builder.toUriString(),List<Member>.class);
return aux;
}


public Member save(Member member){
    return this.memberRepository.save(member);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))

.queryParam("member",member)
;
Member aux = restTemplate.getForObject(builder.toUriString(),Member.class);
return aux;
}


public void loggedAsMember(){
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    List<Authority> authorities = (List<Authority>) userAccount.getAuthorities();
    Assert.isTrue(authorities.get(0).toString().equals("MEMBER"));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedAsMember"))

;
restTemplate.put(builder.toUriString(),null);
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


public Member findOne(int id){
    return this.memberRepository.findOne(id);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))

.queryParam("id",id)
;
Member aux = restTemplate.getForObject(builder.toUriString(),Member.class);
return aux;
}


}