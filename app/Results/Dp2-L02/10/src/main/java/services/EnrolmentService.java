package services;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import domain.Brotherhood;
import domain.Enrolment;
import domain.Member;
import domain.Position;
import domain.StatusEnrolment;
import repositories.EnrolmentRepository;
import Interface.MemberService;
import Interface.BrotherhoodService;
import Interface.MessageService;
import DTO.BrotherhoodService;
import DTO.Brotherhood;
import DTO.MemberService;
import DTO.MessageService;
@Service
@Transactional
public class EnrolmentService {

@Autowired
 private  EnrolmentRepository enrolmentRepository;

@Autowired
 private  MemberService memberService;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  Validator validator;

@Autowired
 private  MessageService messageService;


public Enrolment reconstructEnrolment(Enrolment enrolment,BindingResult binding){
    this.brotherhoodService.securityAndBrotherhood();
    Enrolment result = this.enrolmentRepository.findOne(enrolment.getId());
    // result.setId(enrolment.getId());
    // result.setVersion(enrolment.getVersion());
    result.setPosition(enrolment.getPosition());
    if (result.getPosition() == null)
        result.setStatusEnrolment(StatusEnrolment.PENDING);
    else
        result.setStatusEnrolment(StatusEnrolment.ACCEPTED);
    this.validator.validate(result, binding);
    return result;
}


public void deleteAllEnrolmentsBrotherhood(){
    Brotherhood brotherhood = this.brotherhoodService.loggedBrotherhood();
    List<Enrolment> enrolmentsToDelete = brotherhood.getEnrolments();
    brotherhood.setEnrolments(new ArrayList<Enrolment>());
    List<Member> members = this.memberService.findAll();
    for (Member m : members) {
        List<Enrolment> enrolmentsOfMember = m.getEnrolments();
        enrolmentsOfMember.removeAll(enrolmentsToDelete);
        m.setEnrolments(enrolmentsOfMember);
        this.memberService.save(m);
    }
}


public boolean enrolmentMemberComprobation(Brotherhood brotherhood){
    this.memberService.loggedAsMember();
    Member loggedMember = this.memberService.loggedMember();
    List<Enrolment> enrolmentsBrotherhood = brotherhood.getEnrolments();
    List<Enrolment> enrolmentsMember = loggedMember.getEnrolments();
    enrolmentsBrotherhood.retainAll(enrolmentsMember);
    boolean res = false;
    for (Enrolment e : enrolmentsBrotherhood) if (e.getStatusEnrolment() == StatusEnrolment.ACCEPTED || e.getStatusEnrolment() == StatusEnrolment.PENDING)
        res = true;
    return res;
}


public Enrolment findOne(Integer enrolmentId){
    return this.enrolmentRepository.findOne(enrolmentId);
}


public Enrolment save(Enrolment e){
    return this.enrolmentRepository.save(e);
}


public void rejectEnrolment(Enrolment enrolment){
    this.brotherhoodService.loggedAsBrotherhood();
    Assert.notNull(enrolment);
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.isTrue(enrolment.getStatusEnrolment() == StatusEnrolment.PENDING);
    Assert.isTrue(loggedBrotherhood.getEnrolments().contains(enrolment));
    enrolment.setStatusEnrolment(StatusEnrolment.REJECTED);
    this.save(enrolment);
}


public Enrolment createEnrolment(Brotherhood brotherhood,Enrolment enrolment,Member m){
    Assert.notNull(m);
    this.memberService.loggedAsMember();
    enrolment = this.create();
    enrolment.setBrotherhood(brotherhood);
    enrolment.setMember(m);
    enrolment.setStatusEnrolment(StatusEnrolment.PENDING);
    List<Enrolment> enrolments = new ArrayList<>();
    enrolments = brotherhood.getEnrolments();
    enrolments.add(enrolment);
    brotherhood.setEnrolments(enrolments);
    this.enrolmentRepository.save(enrolment);
    this.brotherhoodService.save(brotherhood);
    return enrolment;
}


public List<Enrolment> findAll(){
    return this.enrolmentRepository.findAll();
}


public void delete(Enrolment e){
    this.enrolmentRepository.delete(e);
}


public void flush(){
    this.enrolmentRepository.flush();
}


public void deleteInBatch(List<Enrolment> list){
    this.enrolmentRepository.deleteInBatch(list);
}


public void dropout(int enrolmentId){
    this.memberService.loggedAsMember();
    Date thisMoment = new Date();
    thisMoment.setTime(thisMoment.getTime() - 1);
    Enrolment enrolment = this.findOne(enrolmentId);
    Assert.notNull(enrolment);
    Assert.isTrue(enrolment.getStatusEnrolment() != StatusEnrolment.DROPOUT);
    Assert.isTrue(enrolment.getDropOutDate() == null);
    Assert.isTrue(this.memberService.loggedMember().getEnrolments().contains(enrolment));
    enrolment.setStatusEnrolment(StatusEnrolment.DROPOUT);
    enrolment.setDropOutDate(thisMoment);
    this.enrolmentRepository.save(enrolment);
    this.messageService.sendNotificationDropOut(enrolment.getBrotherhood());
}


public Enrolment create(){
    Enrolment e = new Enrolment();
    Date creationMoment = new Date();
    creationMoment.setTime(creationMoment.getTime() - 1);
    Position position = null;
    StatusEnrolment statusEnrolment = StatusEnrolment.PENDING;
    Date dropOutDate = null;
    Member member = null;
    Brotherhood brotherhood = null;
    e.setCreationMoment(creationMoment);
    e.setPosition(position);
    e.setStatusEnrolment(statusEnrolment);
    e.setDropOutDate(dropOutDate);
    e.setMember(member);
    e.setBrotherhood(brotherhood);
    return e;
}


public List<Enrolment> getEnrolmentsPerMember(Member m){
    return m.getEnrolments();
}


public Enrolment saveEnrolmentWithCheck(Enrolment enrolment){
    Enrolment enrolmentSaved;
    Assert.isTrue(enrolment.getStatusEnrolment().equals(StatusEnrolment.ACCEPTED));
    Assert.notNull(enrolment.getPosition());
    System.out.println(enrolment.getPosition());
    Assert.notNull(enrolment.getBrotherhood());
    Assert.notNull(enrolment.getMember());
    Assert.notNull(enrolment.getCreationMoment());
    enrolmentSaved = this.save(enrolment);
    return enrolmentSaved;
}


}