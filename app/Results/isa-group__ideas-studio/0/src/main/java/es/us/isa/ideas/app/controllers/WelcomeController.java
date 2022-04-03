package es.us.isa.ideas.app.controllers;
 import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class WelcomeController extends AbstractController{

// Constructors -----------------------------------------------------------
public WelcomeController() {
    super();
}
@RequestMapping(value = "/welcome", method = RequestMethod.GET)
public ModelAndView index(){
    return new ModelAndView("redirect:/app/editor");
}


@RequestMapping(value = "/", method = RequestMethod.GET)
public ModelAndView home(){
    return index();
}


}