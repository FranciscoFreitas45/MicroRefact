package DTO;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.BrotherhoodRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Box;
import domain.Brotherhood;
import domain.Enrolment;
import domain.Float;
import domain.History;
import domain.InceptionRecord;
import domain.LegalRecord;
import domain.LinkRecord;
import domain.Member;
import domain.MiscellaneousRecord;
import domain.Parade;
import domain.PeriodRecord;
import domain.SocialProfile;
import domain.StatusEnrolment;
import forms.FormObjectBrotherhood;
import Interface.BoxService;
import Interface.MessageService;
import Interface.SocialProfileService;
import Interface.EnrolmentService;
import Interface.ParadeService;
import Interface.FloatService;
import Interface.ActorService;
import Interface.RequestService;
import Interface.SponsorshipService;
import DTO.UserAccount;
import DTO.Authority;
import DTO.Enrolment;
import DTO.BoxService;
import DTO.Box;
import DTO.MessageService;
import DTO.SocialProfileService;
import DTO.Member;
import DTO.EnrolmentService;
import DTO.Parade;
import DTO.ParadeService;
import DTO.FloatService;
import DTO.SocialProfile;
import DTO.FormObjectBrotherhood;
public class BrotherhoodService {

 private  BrotherhoodRepository brotherhoodRepository;

 private  BoxService boxService;

 private  Validator validator;

 private  MessageService messageService;

 private  SocialProfileService socialProfileService;

 private  HistoryService historyService;

 private  EnrolmentService enrolmentService;

 private  ParadeService paradeService;

 private  FloatService floatService;

 private  ActorService actorService;

 private  RequestService requestService;

 private  SponsorshipService sponsorshipService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public List<Member> getMembersOfBrotherhood(Brotherhood bro){
    List<Member> members = new ArrayList<Member>();
    List<Enrolment> enrolmentsBro = bro.getEnrolments();
    for (Enrolment e : enrolmentsBro) if (e.getStatusEnrolment() == StatusEnrolment.ACCEPTED)
        members.add(e.getMember());
    return members;
}


public Brotherhood getBrotherhoodByUsername(String username){
    return this.brotherhoodRepository.getBrotherhoodByUsername(username);
}


public List<Parade> getParadesByBrotherhood(Brotherhood b){
    return this.brotherhoodRepository.getParadesByBrotherhood(b.getId());
}


public List<Brotherhood> getBrotherhoodsByArea(Integer areaId){
    return this.brotherhoodRepository.getBrotherhoodByArea(areaId);
}


public List<String> getPositions(){
    String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
    Brotherhood bro = new Brotherhood();
    bro = this.loggedBrotherhood();
    List<String> positions = new ArrayList<String>();
    List<Enrolment> enrolmentsBro = bro.getEnrolments();
    for (Enrolment e : enrolmentsBro) if (e.getStatusEnrolment() == StatusEnrolment.ACCEPTED)
        if (locale.contains("EN"))
            positions.add(e.getPosition().getTitleEnglish());
        else if (locale.contains("ES"))
            positions.add(e.getPosition().getTitleSpanish());
    return positions;
}


public List<Enrolment> getPengingEnrolments(){
    Brotherhood bro = this.loggedBrotherhood();
    List<Enrolment> enrolments = bro.getEnrolments();
    Assert.notNull(bro);
    List<Enrolment> res = new ArrayList<Enrolment>();
    for (Enrolment e : enrolments) if (e.getStatusEnrolment() == StatusEnrolment.PENDING)
        res.add(e);
    return res;
}


public int getBrotherhoodIdByInceptionRecord(int inceptionRecordId){
    return this.brotherhoodRepository.getBrotherhoodIdByInceptionRecord(inceptionRecordId);
}


public int getBrotherhoodIdByPeriodRecord(int periodRecordId){
    return this.brotherhoodRepository.getBrotherhoodIdByPeriodRecord(periodRecordId);
}


public int getBrotherhoodIdByLegalRecord(int legalRecordId){
    return this.brotherhoodRepository.getBrotherhoodIdByLegalRecord(legalRecordId);
}


public Enrolment getEnrolment(Member m){
    Enrolment en = null;
    Brotherhood bro = this.loggedBrotherhood();
    List<Enrolment> broEn = bro.getEnrolments();
    List<Enrolment> memEn = m.getEnrolments();
    broEn.retainAll(memEn);
    for (Enrolment e : broEn) if (e.getStatusEnrolment() == StatusEnrolment.ACCEPTED) {
        en = e;
        break;
    }
    return en;
}


public List<Float> getFloatsByBrotherhood(Brotherhood b){
    return this.brotherhoodRepository.getFloatsByBrotherhood(b.getId());
}


public List<Parade> getParadesByBrotherhoodFinal(Brotherhood b){
    return this.brotherhoodRepository.getParadesByBrotherhoodFinal(b.getId());
}


public Brotherhood loggedBrotherhood(){
    Brotherhood brother = new Brotherhood();
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    brother = this.brotherhoodRepository.getBrotherhoodByUsername(userAccount.getUsername());
    return brother;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedBrotherhood"))

;
Brotherhood aux = restTemplate.getForObject(builder.toUriString(),Brotherhood.class);
return aux;
}


}