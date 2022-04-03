package controllers;
 import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController{

// Constructors -----------------------------------------------------------
public CustomerController() {
    super();
}
@RequestMapping("/action-1")
public ModelAndView action1(){
    ModelAndView result;
    result = new ModelAndView("customer/action-1");
    return result;
}


@RequestMapping("/action-2")
public ModelAndView action2(){
    ModelAndView result;
    result = new ModelAndView("customer/action-2");
    return result;
}


}