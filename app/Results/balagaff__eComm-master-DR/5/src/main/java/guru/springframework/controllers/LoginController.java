package guru.springframework.controllers;
 import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import java.util.HashMap;
import java.util.Map;
@Controller
public class LoginController {

@Autowired
 private  UserService userService;


@RequestMapping(value = "/registration", method = RequestMethod.GET)
public ModelAndView registration(){
    ModelAndView modelAndView = new ModelAndView();
    User user = new User();
    modelAndView.addObject("user", user);
    modelAndView.setViewName("registration");
    Map<String, String> sampleDropdownMap = new HashMap<String, String>();
    sampleDropdownMap.put("IT", "it");
    sampleDropdownMap.put("Manufacturing", "manu");
    sampleDropdownMap.put("Logistics", "log");
    sampleDropdownMap.put("Industrial", "indus");
    modelAndView.addObject("dropDownItems", sampleDropdownMap);
    Map<String, String> sampleDropdownMaps = new HashMap<String, String>();
    sampleDropdownMaps.put("Admin", "a");
    sampleDropdownMaps.put("Ratifier", "r");
    modelAndView.addObject("dropDownItemsa", sampleDropdownMaps);
    return modelAndView;
}


@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
public ModelAndView login(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("login");
    return modelAndView;
}


@RequestMapping(value = "/registration", method = RequestMethod.POST)
public ModelAndView createNewUser(User user,BindingResult bindingResult){
    ModelAndView modelAndView = new ModelAndView();
    User userExists = userService.findUserByEmail(user.getEmail());
    if (userExists != null) {
        bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
    }
    if (bindingResult.hasErrors()) {
        modelAndView.setViewName("registration");
        Map<String, String> sampleDropdownMap = new HashMap<String, String>();
        sampleDropdownMap.put("IT", "it");
        sampleDropdownMap.put("Manufacturing", "manu");
        sampleDropdownMap.put("Logistics", "log");
        sampleDropdownMap.put("Industrial", "indus");
        modelAndView.addObject("dropDownItems", sampleDropdownMap);
        Map<String, String> sampleDropdownMaps = new HashMap<String, String>();
        sampleDropdownMaps.put("Admin", "a");
        sampleDropdownMaps.put("Ratifier", "r");
        modelAndView.addObject("dropDownItemsa", sampleDropdownMaps);
    } else {
        userService.saveUser(user);
        modelAndView.addObject("successMessage", "User has been registered successfully");
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registration");
    }
    // model = new ModelAndView("hello", "dropDownItems", sampleDropdownMap);
    return modelAndView;
}


@RequestMapping(value = "/home", method = RequestMethod.GET)
public ModelAndView home(){
    ModelAndView modelAndView = new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByEmail(auth.getName());
    modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
    modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
    modelAndView.setViewName("home");
    return modelAndView;
}


}