package controllers;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.BrotherhoodService;
import services.EnrolmentService;
import services.MemberService;
import services.MessageService;
import domain.Brotherhood;
import domain.Enrolment;
import domain.Member;
import domain.StatusEnrolment;
@Controller
@RequestMapping("/enrolment/member")
public class EnrolmentController extends AbstractController{

@Autowired
 private  EnrolmentService enrolmentService;

@Autowired
 private  MemberService memberService;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  MessageService messageService;

public EnrolmentController() {
    super();
}
@RequestMapping(value = "/dropout", method = RequestMethod.POST, params = "save")
public ModelAndView dropOut(int enrolmentId){
    ModelAndView result;
    Enrolment enrolment;
    // Date thisMoment = new Date();
    // thisMoment.setTime(thisMoment.getTime() - 1);
    enrolment = this.enrolmentService.findOne(enrolmentId);
    if (enrolment.getStatusEnrolment() != StatusEnrolment.DROPOUT && enrolment.getDropOutDate() == null && this.memberService.loggedMember().getEnrolments().contains(enrolment)) {
        this.enrolmentService.dropout(enrolmentId);
        result = new ModelAndView("redirect:list.do");
    } else
        result = new ModelAndView("redirect:list.do");
    return result;
}


@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
public ModelAndView create(int brotherhoodId){
    ModelAndView result;
    Brotherhood brotherhood = this.brotherhoodService.findOne(brotherhoodId);
    boolean res = this.enrolmentService.enrolmentMemberComprobation(brotherhood);
    Enrolment enrolment = new Enrolment();
    Member member = this.memberService.loggedMember();
    if (!res) {
        this.enrolmentService.createEnrolment(brotherhood, enrolment, member);
        result = this.createEditModelAndView(enrolment);
        result = new ModelAndView("redirect:list.do");
    } else {
        result = this.list();
        result.addObject("res", res);
    }
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView list(){
    ModelAndView result;
    List<Enrolment> enrolments = new ArrayList<Enrolment>();
    enrolments = this.enrolmentService.getEnrolmentsPerMember(this.memberService.loggedMember());
    String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
    result = new ModelAndView("enrolment/member/list");
    result.addObject("enrolments", enrolments);
    result.addObject("locale", locale);
    result.addObject("requestURI", "enrolment/member/list.do");
    return result;
}


public ModelAndView createEditModelAndView(Enrolment enrolment,String messageCode){
    ModelAndView result;
    result = new ModelAndView("enrolment/member/create");
    result.addObject("enrolment", enrolment);
    result.addObject("message", messageCode);
    return result;
}


}