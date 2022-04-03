package DTO;
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
public class EnrolmentService {

 private  EnrolmentRepository enrolmentRepository;

 private  MemberService memberService;

 private  BrotherhoodService brotherhoodService;

 private  Validator validator;

 private  MessageService messageService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public List<Enrolment> getEnrolmentsPerMember(Member m){
    return m.getEnrolments();
}


public void delete(Enrolment e){
    this.enrolmentRepository.delete(e);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))

.queryParam("e",e)
;
restTemplate.put(builder.toUriString(),null);
}


}