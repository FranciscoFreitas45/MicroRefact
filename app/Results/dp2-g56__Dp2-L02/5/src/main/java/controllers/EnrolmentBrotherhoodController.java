package controllers;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.BrotherhoodService;
import services.EnrolmentService;
import services.MessageService;
import services.PositionService;
import domain.Brotherhood;
import domain.Enrolment;
import domain.Position;
import domain.StatusEnrolment;
import Interface.PositionService;
import DTO.PositionService;
@Controller
@RequestMapping("/enrolment/brotherhood")
public class EnrolmentBrotherhoodController extends AbstractController{

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  EnrolmentService enrolmentService;

@Autowired
 private  PositionService positionService;

@Autowired
 private  MessageService messageService;

public EnrolmentBrotherhoodController() {
    super();
}
@RequestMapping(value = "/reject", method = RequestMethod.POST, params = "save")
public ModelAndView reject(int enrolmentId){
    ModelAndView result;
    Enrolment enrolment;
    enrolment = this.enrolmentService.findOne(enrolmentId);
    if (enrolment.getStatusEnrolment() == StatusEnrolment.PENDING && this.brotherhoodService.loggedBrotherhood().getEnrolments().contains(enrolment))
        this.enrolmentService.rejectEnrolment(enrolment);
    result = new ModelAndView("redirect:list.do");
    return result;
}


@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
public ModelAndView save(Enrolment enrolment,BindingResult binding){
    ModelAndView result;
    Enrolment e;
    Brotherhood brotherhood = this.brotherhoodService.securityAndBrotherhood();
    Enrolment enrolmentFounded = this.enrolmentService.findOne(enrolment.getId());
    String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
    List<Position> positions = this.positionService.findAll();
    e = this.enrolmentService.reconstructEnrolment(enrolment, binding);
    if (e.getPosition() == null)
        if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES")) {
            binding.addError(new FieldError("enrolment", "position", enrolment.getPosition(), false, null, null, "La posicion no puede ser nula"));
            return this.createEditModelAndView(enrolment);
        } else {
            binding.addError(new FieldError("enrolment", "position", enrolment.getPosition(), false, null, null, "Position cannot be null"));
            return this.createEditModelAndView(enrolment);
        }
    if (binding.hasErrors()) {
        result = new ModelAndView("enrolment/brotherhood/assignPosition");
        result.addObject("positions", positions);
        result.addObject("enrolment", enrolment);
        result.addObject("locale", locale);
    } else
        try {
            this.enrolmentService.saveEnrolmentWithCheck(e);
            this.messageService.sendNotificationBroEnrolMem(e.getMember());
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = new ModelAndView("enrolment/brotherhood/assignPosition");
            result.addObject("positions", positions);
            result.addObject("enrolment", enrolment);
            result.addObject("locale", locale);
            result.addObject("message", "enrolment.commit.error");
        }
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView list(){
    ModelAndView result;
    List<Enrolment> enrolments = new ArrayList<Enrolment>();
    enrolments = this.brotherhoodService.getPengingEnrolments();
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Boolean hasArea = !(loggedBrotherhood.getArea() == null);
    result = new ModelAndView("enrolment/brotherhood/list");
    result.addObject("enrolments", enrolments);
    result.addObject("hasArea", hasArea);
    result.addObject("requestURI", "enrolment/brotherhood/list.do");
    return result;
}


public ModelAndView createEditModelAndView(Enrolment enrolment,String messageCode){
    ModelAndView result;
    String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
    List<Position> positions = this.positionService.findAll();
    result = new ModelAndView("enrolment/brotherhood/assignPosition");
    result.addObject("positions", positions);
    result.addObject("enrolment", enrolment);
    result.addObject("locale", locale);
    return result;
}


@RequestMapping(value = "/assignPosition", method = RequestMethod.GET)
public ModelAndView assingPosition(int enrolmentId){
    ModelAndView result;
    String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
    Brotherhood brotherhood = this.brotherhoodService.securityAndBrotherhood();
    Enrolment enrolment = this.enrolmentService.findOne(enrolmentId);
    List<Position> positions = this.positionService.findAll();
    if (enrolment.getId() != 0 && brotherhood.getEnrolments().contains(enrolment) && enrolment.getStatusEnrolment() == StatusEnrolment.PENDING) {
        result = new ModelAndView("enrolment/brotherhood/assignPosition");
        result.addObject("positions", positions);
        result.addObject("enrolment", enrolment);
        result.addObject("locale", locale);
    } else
        result = new ModelAndView("redirect:list.do");
    return result;
}


}