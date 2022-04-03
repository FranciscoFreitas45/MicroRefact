package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EnrolmentController {

 private Enrolment enrolment;

 private Enrolment enrolment;


@PutMapping
("/setBrotherhood")
public void setBrotherhood(@RequestParam(name = "brotherhood") Brotherhood brotherhood){
enrolment.setBrotherhood(brotherhood);
}


@PutMapping
("/setMember")
public void setMember(@RequestParam(name = "member") Member member){
enrolment.setMember(member);
}


}