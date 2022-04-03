package controllers;
 import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController{


@RequestMapping("/action-1")
public ModelAndView action1(){
    ModelAndView result;
    result = new ModelAndView("profile/action-1");
    return result;
}


@RequestMapping("/action-2")
public ModelAndView action2(){
    ModelAndView result;
    result = new ModelAndView("profile/action-2");
    return result;
}


@RequestMapping("/action-3")
public ModelAndView action3(){
    throw new RuntimeException("Oops! An *expected* exception was thrown. This is normal behaviour.");
}


}