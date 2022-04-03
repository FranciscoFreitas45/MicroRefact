package controllers;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.BrotherhoodService;
import services.EnrolmentService;
import services.MemberService;
import domain.Enrolment;
import domain.Member;
import domain.StatusEnrolment;
@Controller
@RequestMapping("/member/brotherhood")
public class MemberBrotherhoodController extends AbstractController{

@Autowired
 private  MemberService memberService;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  EnrolmentService enrolmentService;

public MemberBrotherhoodController() {
    super();
}
@RequestMapping(value = "/expelled", method = RequestMethod.POST, params = "save")
public ModelAndView expelled(int memberId){
    ModelAndView result;
    Member member;
    Enrolment e;
    member = this.memberService.findOne(memberId);
    List<Enrolment> enrolments = member.getEnrolments();
    Assert.notNull(member);
    e = this.brotherhoodService.getEnrolment(member);
    if (e.getStatusEnrolment() == StatusEnrolment.ACCEPTED) {
        enrolments.remove(e);
        e.setStatusEnrolment(StatusEnrolment.EXPELLED);
        this.enrolmentService.save(e);
        enrolments.add(e);
        member.setEnrolments(enrolments);
        this.memberService.save(member);
        result = new ModelAndView("redirect:list.do");
    } else
        result = new ModelAndView("redirect:/");
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView list(){
    ModelAndView result;
    List<Member> members = new ArrayList<Member>();
    List<String> positions = new ArrayList<String>();
    members = this.brotherhoodService.getMembersOfBrotherhood();
    positions = this.brotherhoodService.getPositions();
    result = new ModelAndView("member/brotherhood/list");
    result.addObject("members", members);
    result.addObject("positions", positions);
    result.addObject("requestURI", "member/brotherhood/list.do");
    return result;
}


}