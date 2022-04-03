package services;
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
@Service
@Transactional
public class MemberService {

@Autowired
 private  MemberRepository memberRepository;

@Autowired
 private  FinderService finderService;

@Autowired
 private  BoxService boxService;

@Autowired
 private  Validator validator;

@Autowired
 private  MessageService messageService;

@Autowired
 private  ParadeService paradeService;

@Autowired
 private  RequestService requestService;

@Autowired
 private  EnrolmentService enrolmentService;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  ActorService actorService;


public Member createMember(){
    Member member = new Member();
    // Se crean las listas vacías
    // ACTOR
    List<SocialProfile> socialProfiles = new ArrayList<>();
    List<Box> boxes = new ArrayList<>();
    // MEMBER
    Finder finder = this.finderService.createFinder();
    List<Enrolment> enrolments = new ArrayList<>();
    List<Request> requests = new ArrayList<>();
    UserAccount userAccount = new UserAccount();
    userAccount.setUsername("");
    userAccount.setPassword("");
    // Actor
    member.setAddress("");
    member.setBoxes(boxes);
    member.setEmail("");
    member.setEnrolments(enrolments);
    member.setFinder(finder);
    member.setHasSpam(false);
    member.setMiddleName("");
    member.setName("");
    member.setPhoneNumber("");
    member.setPhoto("");
    member.setPolarity(0.0);
    member.setRequests(requests);
    member.setSocialProfiles(socialProfiles);
    member.setSurname("");
    List<Authority> authorities = new ArrayList<Authority>();
    Authority authority = new Authority();
    authority.setAuthority(Authority.MEMBER);
    authorities.add(authority);
    userAccount.setAuthorities(authorities);
    userAccount.setIsNotLocked(true);
    member.setUserAccount(userAccount);
    return member;
}


public Member loggedMember(){
    Member m = new Member();
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    m = this.memberRepository.getMemberByUsername(userAccount.getUsername());
    return m;
}


public Member updateMember(Member member){
    this.loggedAsMember();
    Assert.isTrue(member.getId() != 0 && this.loggedMember().getId() == member.getId());
    return this.memberRepository.save(member);
}


public String SocialProfilesToString(){
    String res = "";
    Member member = this.loggedMember();
    List<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();
    StringBuilder sb = new StringBuilder();
    socialProfiles = member.getSocialProfiles();
    Integer cont = 1;
    for (SocialProfile f : socialProfiles) {
        sb.append("Profile" + cont + " Name: " + f.getName() + " Nick: " + f.getNick() + " Profile link: " + f.getProfileLink()).append(System.getProperty("line.separator"));
        cont++;
    }
    return sb.toString();
}


public Member securityAndMember(){
    UserAccount userAccount = LoginService.getPrincipal();
    String username = userAccount.getUsername();
    Member loggedMember = this.memberRepository.getMemberByUsername(username);
    List<Authority> authorities = (List<Authority>) loggedMember.getUserAccount().getAuthorities();
    Assert.isTrue(authorities.get(0).toString().equals("MEMBER"));
    return loggedMember;
}


public Member findOne(int id){
    return this.memberRepository.findOne(id);
}


public Member save(Member member){
    return this.memberRepository.save(member);
}


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
}


public List<Member> findAll(){
    return this.memberRepository.findAll();
}


public void delete(Member member){
    this.memberRepository.delete(member);
}


public Member saveCreate(Member member){
    List<Box> boxes = new ArrayList<>();
    // Se crean las listas vacías
    // ACTOR
    List<SocialProfile> socialProfiles = new ArrayList<>();
    member.setSocialProfiles(socialProfiles);
    // MEMBER
    List<Enrolment> enrolments = new ArrayList<>();
    member.setEnrolments(enrolments);
    List<Request> requests = new ArrayList<>();
    member.setRequests(requests);
    // Boxes
    Box box1 = this.boxService.createSystem();
    box1.setName("INBOX");
    Box saved1 = this.boxService.saveSystem(box1);
    boxes.add(saved1);
    Box box2 = this.boxService.createSystem();
    box2.setName("OUTBOX");
    Box saved2 = this.boxService.saveSystem(box2);
    boxes.add(saved2);
    Box box3 = this.boxService.createSystem();
    box3.setName("TRASHBOX");
    Box saved3 = this.boxService.saveSystem(box3);
    boxes.add(saved3);
    Box box4 = this.boxService.createSystem();
    box4.setName("SPAMBOX");
    Box saved4 = this.boxService.saveSystem(box4);
    boxes.add(saved4);
    Box box5 = this.boxService.createSystem();
    box5.setName("NOTIFICATIONBOX");
    Box saved5 = this.boxService.saveSystem(box5);
    boxes.add(saved5);
    member.setBoxes(boxes);
    Finder savedFinder = new Finder();
    savedFinder = this.finderService.save(this.finderService.createFinder());
    member.setFinder(savedFinder);
    Member saved = new Member();
    saved = this.memberRepository.save(member);
    return saved;
}


public void deleteLoggedMember(){
    Member member = this.securityAndMember();
    this.messageService.updateSendedMessageByLogguedActor();
    this.messageService.updateReceivedMessageToLogguedActor();
    this.messageService.deleteAllMessageFromActor();
    List<Request> requestsToDelete = member.getRequests();
    List<Parade> parades = this.paradeService.findAll();
    for (Parade p : parades) {
        List<Request> requestsOfParade = p.getRequests();
        requestsOfParade.removeAll(requestsToDelete);
        p.setRequests(requestsOfParade);
        this.paradeService.save(p);
    }
    member.setRequests(new ArrayList<Request>());
    this.save(member);
    for (Request r : requestsToDelete) this.requestService.delete(r);
    List<Enrolment> enrolmentsToDelete = member.getEnrolments();
    List<Brotherhood> brotherhoods = this.brotherhoodService.findAll();
    for (Brotherhood b : brotherhoods) {
        List<Enrolment> enrolmentsOfBrotherhood = b.getEnrolments();
        enrolmentsOfBrotherhood.removeAll(enrolmentsToDelete);
        b.setEnrolments(enrolmentsOfBrotherhood);
        this.brotherhoodService.save(b);
    }
    member.setEnrolments(new ArrayList<Enrolment>());
    this.save(member);
    for (Enrolment e : enrolmentsToDelete) this.enrolmentService.delete(e);
    this.delete(member);
    this.flush();
}


public void flush(){
    this.memberRepository.flush();
}


public void loggedAsMember(){
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    List<Authority> authorities = (List<Authority>) userAccount.getAuthorities();
    Assert.isTrue(authorities.get(0).toString().equals("MEMBER"));
}


}