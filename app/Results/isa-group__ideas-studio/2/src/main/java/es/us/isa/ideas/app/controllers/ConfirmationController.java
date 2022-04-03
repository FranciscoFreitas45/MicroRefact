package es.us.isa.ideas.app.controllers;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import es.us.isa.ideas.app.entities.Confirmation;
import es.us.isa.ideas.app.services.ConfirmationService;
@Controller
@RequestMapping("/confirm")
public class ConfirmationController extends AbstractController{

@Autowired
 private ConfirmationService confirmationService;


@RequestMapping("/registration")
public ModelAndView confirmRegistration(String code){
    ModelAndView result = null;
    try {
        Confirmation confirmation = confirmationService.confirmRegistration(code);
        result = new ModelAndView("confirmation/confirmationSucess");
        result.addObject("actor", confirmation.getResearcher());
        result.addObject("subject", "registration");
        result.addObject("userAccount", confirmation.getResearcher().getUserAccount());
        result.addObject("confirmationCode", code);
    } catch (Throwable oops) {
        result = new ModelAndView("confirmation/confirmationError");
        result.addObject("confirmationCode", code);
        result.addObject("message", "registration.error.inexistentCode");
    }
    return result;
}


@RequestMapping("/passwordReset")
public ModelAndView confirmPasswordReset(String code){
    ModelAndView result = null;
    try {
        Confirmation confirmation = confirmationService.resetPassword(code);
        result = new ModelAndView("confirmation/confirmationResetSucess");
        result.addObject("actor", confirmation.getResearcher());
        result.addObject("subject", "password reset");
        result.addObject("userAccount", confirmation.getResearcher().getUserAccount());
        result.addObject("confirmationCode", code);
    } catch (Throwable oops) {
        result = new ModelAndView("confirmation/confirmationError");
        result.addObject("confirmationCode", code);
        result.addObject("message", "registration.error.inexistentCode");
    }
    return result;
}


}