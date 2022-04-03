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


}